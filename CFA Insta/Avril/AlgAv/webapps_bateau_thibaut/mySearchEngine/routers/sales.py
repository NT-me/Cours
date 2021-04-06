from fastapi import APIRouter, HTTPException
import requests
import utils as u
from mySearchEngine.data.models import Products
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker


ADRESS_CANVA = u.ADRESS_CANVA

router = APIRouter(
            tags=["sales", "products", "index"]
            )

db_string = u.DB_PATH
engine = create_engine(db_string, connect_args={'check_same_thread': False})
Session = sessionmaker(engine)
session = Session()


@router.get("/onsaleproducts")
def show_all_salesproducts():
    resDB = session.query(Products).filter(Products.sale == 1)
    res = []
    for item in resDB:
        r = requests.get(url=ADRESS_CANVA + "tig/product/{}".format(item.pid))
        res.append(resDB.first().retValue(r.json()))
    return res


@router.get("/onsaleproduct/{id}")
def show_one_salesproducts(id):
    resDB = session.query(Products).filter(Products.sale == 1, Products.pid == id)
    if resDB.all():
        r = requests.get(url=ADRESS_CANVA + "tig/product/{}".format(id))
        try:
            if r.json()["detail"] == "Not found.":
                raise HTTPException(status_code=404, detail="Item not found")
        except KeyError:
            pass
        return resDB.first().retValue(r.json())
    else:
        raise HTTPException(status_code=404, detail="Item not avaible")


@router.get("/putonsale/{id}/{newprice}")
def putonsale_one_product(id: int, newprice: float):
    resDB = session.query(Products)\
    .filter(Products.pid == id)
    updb = resDB.update({Products.sale: True, Products.discount: newprice}, synchronize_session = False)
    session.commit()
    if resDB.all():
        r = requests.get(url=ADRESS_CANVA + "tig/product/{}".format(id))
        try:
            if r.json()["detail"] == "Not found.":
                raise HTTPException(status_code=404, detail="Item not found")
        except KeyError:
            pass
        return resDB.first().retValue(r.json())
    else:
        raise HTTPException(status_code=404, detail="Item not avaible")


@router.get("/removesale/{id}")
def remove_sale_one_product(id: int):
    resDB = session.query(Products)\
    .filter(Products.pid == id, Products.sale)
    if resDB.all():
        r = requests.get(url=ADRESS_CANVA + "tig/product/{}".format(id))
        try:
            if r.json()["detail"] == "Not found.":
                raise HTTPException(status_code=404, detail="Item not found")
        except KeyError:
            res = resDB.first().retValue(r.json())
            updb = resDB.update({Products.sale: False}, synchronize_session = False)
            session.commit()
            res["sale"] = False # Forçage pour l'affichage, la donnée est correctement modifée a la ligne du dessus
            return res
    else:
        raise HTTPException(status_code=404, detail="Item not on sale")
