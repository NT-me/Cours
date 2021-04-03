from fastapi import APIRouter, HTTPException
import requests
import utils as u

ADRESS_CANVA = u.ADRESS_CANVA

router = APIRouter(
            tags=["shipPoints", "redirects"]
            )


@router.get("/shipPoints")
def show_all_shipPoints():
    r = requests.get(url=ADRESS_CANVA + "tig/shipPoints/")
    return r.json()


@router.get("/shipPoint/{id}")
def show_one_shipPoint(id):
    r = requests.get(url=ADRESS_CANVA + "tig/shipPoint/{}".format(id))
    try:
        if r.json()["detail"] == "Not found.":
            raise HTTPException(status_code=404, detail="Item not found")
    except KeyError:
        pass
    return r.json()
