import utils as u
from fastapi import FastAPI
from mySearchEngine.routers import products, shipPoints, available, sales, category, infoproduct, stocks
from mySearchEngine.tasks import fetchData as fd
from mySearchEngine.data.models import Products, Base
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker


app = FastAPI()
BASE_URL = u.ADRESS_CANVA

db_string = u.DB_PATH
db_sal = create_engine(db_string, connect_args={'check_same_thread': False})
Session = sessionmaker(db_sal)
session = Session()
Base.metadata.create_all(db_sal)
fd.fetchProducts()


# app.include_router(products.router)
app.include_router(available.router)
app.include_router(shipPoints.router)
app.include_router(category.router)
app.include_router(sales.router)
app.include_router(infoproduct.router)
app.include_router(stocks.router)


@app.get("/helloworld")
def read_root():
    return {"Hello": "World"}
