from fastapi import APIRouter, HTTPException
import requests
from mySearchEngine import DB_file as DB
from apscheduler.schedulers.background import BackgroundScheduler
from fastapi.responses import RedirectResponse

app = APIRouter()
ADRESS_CANVA = "http://51.255.166.155:1352/"
sched = BackgroundScheduler()


def updateDB():
    r = requests.get(url=ADRESS_CANVA + "tig/products")
    DB.updateProductsTable(r.json())


sched.add_job(updateDB, 'interval', minutes=2)

sched.start()


@app.get("/helloworld")
def read_root():
    DB.initDB()
    return {"Hello": "World"}


@app.get("/products/")
def show_all_prodcuts():
    r = requests.get(url=ADRESS_CANVA + "tig/products")
    return r.json()


@app.get("/product/{id}")
def show_one_product(id):
    r = requests.get(url=ADRESS_CANVA + "tig/product/{}".format(id))
    try:
        if r.json()["detail"] == "Not found.":
            raise HTTPException(status_code=404, detail="Item not found")
    except KeyError:
        pass
    return r.json()


@app.get("/shipPoints/")
def show_all_shipPoints():
    r = requests.get(url=ADRESS_CANVA + "tig/shipPoints/")
    return r.json()


@app.get("/shipPoint/{id}")
def show_one_shipPoint(id):
    r = requests.get(url=ADRESS_CANVA + "tig/shipPoint/{}".format(id))
    try:
        if r.json()["detail"] == "Not found.":
            raise HTTPException(status_code=404, detail="Item not found")
    except KeyError:
        pass
    return r.json()


@app.get("/availableproducts/")
def show_all_availableproducts():
    r = DB.simpleItemSearch("availability", True)
    res = []
    for item in r:
        res.append(item["name"])
    return res


@app.get("/availableproduct/{id}")
def show_one_availableproducts(id):
    r = DB.searchProductById(int(id))
    if r and r[0]["availability"] == True:
        return r
    else:
        raise HTTPException(status_code=404, detail="Item not found")


@app.get("/onsaleproducts/")
def show_all_onsaleproducts():
    r = DB.simpleItemSearch("sale", True)
    res = []
    for item in r:
        res.append(item["name"])
    return res


@app.get("/onsaleproduct/{id}")
def show_one_onsaleproduct(id):
    r = DB.searchProductById(int(id))
    if r and r[0]["sale"] == True:
        return r
    else:
        raise HTTPException(status_code=404, detail="Item not found")


@app.get("/poissons/")
def show_all_fishes():
    r = DB.simpleItemSearch("category", 0)
    res = []
    for item in r:
        res.append(item)
    return res


@app.get("/crustaces/")
def show_all_crustaces():
    r = DB.simpleItemSearch("category", 2)
    res = []
    for item in r:
        res.append(item)
    return res


@app.get("/coquillages/")
def show_all_coquillages():
    r = DB.simpleItemSearch("category", 1)
    res = []
    for item in r:
        res.append(item)
    return res


@app.get("/product/{id}/image/{i_id}")
def show_image(id, i_id):
    ret = DB.iurlById(int(id))
    i_id = int(i_id)
    if i_id is None or i_id == 1:
        print("hello")
        return RedirectResponse(ret.url0)
    elif i_id == 2:
        return RedirectResponse(ret.url1)
    elif i_id == 3:
        return RedirectResponse(ret.url2)
