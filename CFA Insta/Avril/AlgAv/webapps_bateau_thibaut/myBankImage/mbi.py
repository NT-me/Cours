import utils as u
from fastapi import FastAPI
from myBankImage.routers import images
# from mySearchEngine.tasks import fetchData as fd
from myBankImage.data.models import Base
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker


app = FastAPI()
BASE_URL = u.ADRESS_CANVA

db_string = u.DB_IMG_PATH
db_sal = create_engine(db_string, connect_args={'check_same_thread': False})
Session = sessionmaker(db_sal)
session = Session()
Base.metadata.create_all(db_sal)
# fd.fetchProducts()

app.include_router(images.router)


@app.get("/helloworld")
def read_root():
    return {"Hello": "World"}
