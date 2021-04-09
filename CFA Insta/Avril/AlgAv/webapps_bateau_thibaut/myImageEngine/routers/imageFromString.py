from fastapi import APIRouter, HTTPException
import utils as u
from fastapi.responses import RedirectResponse
from myImageEngine.data.models import Image, Tags, Tagmap
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker

db_string = u.DB_MIE_PATH
db_sal = create_engine(db_string, connect_args={'check_same_thread': False})
Session = sessionmaker(db_sal)
session = Session()

router = APIRouter(
            tags=["images"]
            )


@router.get("/myImageFromString/{tag}")
def one_tag(tag):
    try:
        tag_pk = (session.query(Tags).filter(Tags.value == tag)).first().pk
    except AttributeError:
        tag_pk = None
    if tag_pk:
        list_tagmap = (session.query(Tagmap).filter(Tagmap.pk_tag == tag_pk)).all()
        res = []
        for item in list_tagmap:
            url = {"url": ((session.query(Image).filter(Image.pk == item.pk_image)).first()).url}
            res.append(url)
        return res
    else:
        raise HTTPException(status_code=404, detail="Item not found")


@router.get("/myImageFromManyString/{expr_tag}")
def many_tags(expr_tag: str):
    tagsFromExpr = expr_tag.split(" ")
    list_tagmap = []

    for item in tagsFromExpr:
        try:
            tag_pk = (session.query(Tags).filter(Tags.value == item)).first().pk
            list_tagmap = [*list_tagmap, *((session.query(Tagmap).filter(Tagmap.pk_tag == tag_pk)).all())]
        except AttributeError:
            tag_pk = None

    if list_tagmap is not []:
        res = []
        for item in list_tagmap:
            url = {"url": ((session.query(Image).filter(Image.pk == item.pk_image)).first()).url}
            res.append(url)
        return res
    else:
        raise HTTPException(status_code=404, detail="Item not found")


@router.get("/product/{id}/image/")
def show_one_product_main_image(id):
    return RedirectResponse("http://127.0.0.1:8000/mbi/myImage/random/")
