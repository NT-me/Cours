from fastapi import APIRouter, HTTPException
import requests
import utils as u
from mySearchEngine.data.models import Products
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker


ADRESS_CANVA = u.ADRESS_CANVA

router = APIRouter(
            tags=["category", "products", "index"]
            )

db_string = u.DB_PATH
engine = create_engine(db_string, connect_args={'check_same_thread': False})
Session = sessionmaker(engine)
session = Session()


def listProductsByCategory(category):
    resDB = session.query(Products).filter(Products.category == category)
    res = []
    for item in resDB:
        r = requests.get(url=ADRESS_CANVA + "tig/product/{}".format(item.pid))
        res.append(r.json())
    return res


@router.get("/poissons")
def show_all_fishes():
    return listProductsByCategory("POI")


@router.get("/crustaces")
def show_all_cru():
    return listProductsByCategory("CRU")


@router.get("/coquillages")
def show_all_coq():
    return listProductsByCategory("COQ")
