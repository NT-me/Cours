from fastapi import FastAPI
from mySearchEngine import mse
from myBankImage import mbi

app = FastAPI()
app_mse = mse.app
app_mbi = mbi.app

app.mount("/mse", app_mse)
app.mount("/mbi", app_mbi)
