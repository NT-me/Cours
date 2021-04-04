from fastapi import FastAPI
from mySearchEngine import mse
from myBankImage import mbi
from myImageEngine import mie

app = FastAPI()
app_mse = mse.app
app_mbi = mbi.app
app_mie = mie.app

app.mount("/mse", app_mse)
app.mount("/mbi", app_mbi)
app.mount("/mie", app_mie)
# mie.populateDB()
