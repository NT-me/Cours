from fastapi import APIRouter, HTTPException
import requests
import utils as u
from fastapi.responses import RedirectResponse
from typing import Optional

ADRESS_CANVA = u.ADRESS_CANVA

router = APIRouter(
            tags=["products", "redirects"]
            )


@router.get("/products")
def show_all_prodcuts():
    r = requests.get(url=ADRESS_CANVA + "tig/products")
    return r.json()


@router.get("/product/{id}")
def show_one_product(id):
    r = requests.get(url=ADRESS_CANVA + "tig/product/{}".format(id))
    try:
        if r.json()["detail"] == "Not found.":
            raise HTTPException(status_code=404, detail="Item not found")
    except KeyError:
        pass
    return r.json()


@router.get("/product/{id}/image/{i_id}")
def show_one_product_image(id, i_id, redirect: Optional[bool]):
    if redirect:
        return RedirectResponse("http://127.0.0.1:8000/mbi/myImage/random/")
    else:
        r = requests.get(url="http://127.0.0.1:8000/mbi/myImage/random/?redirect=false")
        return r.json()


@router.get("/product/{id}/image/")
def show_one_product_main_image(id):
    return RedirectResponse("http://127.0.0.1:8000/mbi/myImage/random/")
