import utils as u
from fastapi import FastAPI
# from myImageEngine.routers import products, shipPoints, available, sales, category
from myImageEngine.tasks import fetchImages as fd
from myImageEngine.data.models import Image, Base
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker


app = FastAPI()
# BASE_URL = u.ADRESS_CANVA
#
db_string = u.DB_MIE_PATH
db_sal = create_engine(db_string, connect_args={'check_same_thread': False})
Session = sessionmaker(db_sal)
session = Session()
Base.metadata.create_all(db_sal)


@app.get("/helloworld")
def read_root():
    fd.fetchImages()
    return {"Hello": "World"}
