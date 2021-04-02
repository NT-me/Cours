from fastapi import APIRouter, HTTPException
from myBankImage import image_db as DB
from fastapi.responses import RedirectResponse

app = APIRouter()


@app.get("/helloworld")
def read_root():
    DB.initDB()
    return {"Hello": "World"}


@app.get("/random/")
def show_random_image():
    ret = DB.randImage()
    return RedirectResponse(ret.url)


@app.get("/{id}/")
def show_one_image(id):
    ret = DB.iurlById(int(id))
    if ret:
        return RedirectResponse(ret.url)
    else:
        raise HTTPException(status_code=404, detail="Item not found")
