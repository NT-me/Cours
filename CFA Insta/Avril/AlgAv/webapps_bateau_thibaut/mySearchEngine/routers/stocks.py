from fastapi import APIRouter, HTTPException
import requests
import utils as u
from mySearchEngine.data.models import Products
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker


ADRESS_CANVA = u.ADRESS_CANVA

router = APIRouter(
            tags=["stocks", "products", "index"]
            )

db_string = u.DB_PATH
engine = create_engine(db_string, connect_args={'check_same_thread': False})
Session = sessionmaker(engine)
session = Session()


def autoProm(remoteJSON: dict, pr: Products, value: int):
    newQuant = pr.quantityInStock + value
    price = remoteJSON["price"]
    discountPrice = remoteJSON["discount"]
    autoSale = False
    if newQuant > 16:
        if newQuant <= 64:
            discountPrice = float(price) * 0.8
        else:
            discountPrice = float(price) * 0.5
        autoSale = True
    return discountPrice, autoSale


@router.get("/incrementStock/{id}/{incrValue}")
def incrementStock_one_product(id: int, incrValue: int):
    resDB = session.query(Products)\
    .filter(Products.pid == id)
    if resDB.all() and incrValue > 0:
        r = requests.get(url=ADRESS_CANVA + "tig/product/{}".format(id))
        try:
            if r.json()["detail"] == "Not found.":
                raise HTTPException(status_code=404, detail="Item not found")
        except KeyError:
            newQuant = resDB.first().quantityInStock + incrValue
            discountPrice, autoSale = autoProm(r.json(), resDB.first(), incrValue)

            updb = resDB.update({Products.avaible: True,
            Products.quantityInStock: newQuant,
            Products.sale: autoSale,
            Products.discount: discountPrice}, synchronize_session = False)
            session.commit()
            return resDB.first().retValue(r.json())
    else:
        raise HTTPException(status_code=404, detail="Item not avaible or strange value")


@router.get("/decrementStock/{id}/{decrValue}")
def decrementStock_one_product(id: int, decrValue: int):
    resDB = session.query(Products)\
    .filter(Products.pid == id, Products.avaible == 1)
    if resDB.all() and decrValue > 0:
        r = requests.get(url=ADRESS_CANVA + "tig/product/{}".format(id))
        try:
            if r.json()["detail"] == "Not found.":
                raise HTTPException(status_code=404, detail="Item not found")
        except KeyError:
            stillInStock = True
            if ((resDB.first().quantityInStock - decrValue) <= 0):
                decrValue = resDB.first().quantityInStock
                stillInStock = False
            newQuant = resDB.first().quantityInStock - decrValue
            discountPrice, autoSale = autoProm(r.json(), resDB.first(), 0-decrValue)
            res = resDB.first().retValue(r.json())
            res["available"] = stillInStock

            updb = resDB.update({Products.avaible: stillInStock,
            Products.quantityInStock: newQuant,
            Products.sale: autoSale,
            Products.discount: discountPrice}, synchronize_session=False)
            session.commit()
            return res
    else:
        raise HTTPException(status_code=404, detail="Item not avaible or strange value")
