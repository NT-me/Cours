from tinydb import TinyDB, Query
from mySearchEngine.DB.models import Image, Base
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker

db_string = "sqlite:///mySearchEngine/DB/apiDB.db"
db_sal = create_engine(db_string, connect_args={'check_same_thread': False})
Session = sessionmaker(db_sal)
session = Session()
Base.metadata.create_all(db_sal)

session.commit()

db = TinyDB('./mySearchEngine/DB/tme1.json')


def updateProductsTable(dataDict):
    if dataDict:
        for item in dataDict:
            itemQuery = Query()
            itemId = item["id"]
            pic = Image(pid = itemId, url0 = "https://www.qwant.com/?client=brz-moz&t=images&q=1+png&o=0%3AE3567E0BEF14B84E2E1D74A1F71D01B2B8DEF59A", url1 = "https://www.qwant.com/?client=brz-moz&t=images&q=2+png&o=0%3A7596E7C90538744A1D2D68923EF5710ECA1E7FDD", url2 = "https://www.qwant.com/?client=brz-moz&q=3+png&t=images&o=0%3AAB91915788D4C01EAC4E222AF916A77C61DD3913")
            session.add(pic)
            session.commit()
            if db.search(itemQuery.id == itemId):
                db.update(item, itemQuery.id == itemId)
            else:
                db.insert(item)


def iurlById(id):
    resdb = session.query(Image).filter(Image.pid == id)
    return resdb.first()


def searchProductById(id):
    itemQuery = Query()
    itemQuery.id == id
    return db.search(itemQuery.id == id)


def simpleItemSearch(col_name, col_val):
    itemQuery = Query()
    return db.search(getattr(itemQuery, col_name) == col_val)


def allDB():
    return db.all()
