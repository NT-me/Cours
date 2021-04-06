import requests
import utils as u
from mySearchEngine.data.models import Products
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
from apscheduler.schedulers.background import BackgroundScheduler
import random as ran

sched = BackgroundScheduler()
db_string = u.DB_PATH
db_sal = create_engine(db_string, connect_args={'check_same_thread': False})
Session = sessionmaker(db_sal)
session = Session()


def fetchProducts():
    print("Fetch DATA from main server")
    r = requests.get(url=u.ADRESS_CANVA + "tig/products")
    pList = r.json()

    for item in pList:
        pid = item["id"]
        available = 1 if item["availability"] else 0
        sale = 1 if item["sale"] else 0
        category = "POI" if item["category"] == 0 else ("CRU" if item["category"] == 1 else "COQ")
        quantityInStock = ran.randint(1, 20) if item["availability"] else 0
        discount = item["discount"]
        pr = Products(pid=pid, avaible=available, sale=sale, category=category, quantityInStock=quantityInStock, discount=discount)
        try:
            session.add(pr)
            session.commit()
        except:
            pass


sched.add_job(fetchProducts, 'interval', minutes=2)

sched.start()
