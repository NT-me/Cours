from fastapi import APIRouter, HTTPException
from fastapi.responses import RedirectResponse
import utils as u
from myBankImage.data.models import Image
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker


ADRESS_CANVA = u.ADRESS_CANVA

router = APIRouter(
            prefix="/myImage",
            tags=["images", "products"]
            )

db_string = u.DB_IMG_PATH
engine = create_engine(db_string, connect_args={'check_same_thread': False})
Session = sessionmaker(engine)
session = Session()


@router.get("/random/")
def show_random_image():
    import random
    rand = random.randrange(0, session.query(Image).count())
    row = session.query(Image)[rand]
    return RedirectResponse(row.url)


@router.get("/{id}/")
def show_one_image(id):
    resDB = session.query(Image).filter(Image.pk == id)
    ret = resDB.first()
    if ret:
        return RedirectResponse(ret.url)
    else:
        raise HTTPException(status_code=404, detail="Item not found")
