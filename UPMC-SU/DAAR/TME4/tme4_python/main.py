from typing import Optional
from fastapi import FastAPI
from elasticsearch import Elasticsearch


app = FastAPI()
es = Elasticsearch([{'host': 'localhost', 'port': 9200}])

@app.get("/")
async def read_root():
    if es.ping():
        return("connect")
    else:
        return("Not connect")


@app.get("/test")
async def read_item():
    res = es.search(index="articles ", query={"match_all": {}})
    return res
