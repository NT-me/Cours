from fastapi import APIRouter, HTTPException
import requests
import utils as u
from mySearchEngine.data.models import Products
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker


ADRESS_CANVA = u.ADRESS_CANVA

router = APIRouter(
            tags=["availableproducts", "products", "index"]
            )

db_string = u.DB_PATH
engine = create_engine(db_string, connect_args={'check_same_thread': False})
Session = sessionmaker(engine)
session = Session()


@router.get("/availableproducts")
def show_all_availableproducts():
    resDB = session.query(Products).filter(Products.avaible == 1)
    res = []
    for item in resDB:
        r = requests.get(url=ADRESS_CANVA + "tig/product/{}".format(item.pid))
        res.append(r.json())
    return res


@router.get("/availableproduct/{id}")
def show_one_availableproducts(id):
    resDB = session.query(Products).filter(Products.avaible == 1, Products.pid == id)
    if resDB.all():
        r = requests.get(url=ADRESS_CANVA + "tig/product/{}".format(id))
        try:
            if r.json()["detail"] == "Not found.":
                raise HTTPException(status_code=404, detail="Item not found")
        except KeyError:
            pass
        return r.json()
    else:
        raise HTTPException(status_code=404, detail="Item not avaible")
