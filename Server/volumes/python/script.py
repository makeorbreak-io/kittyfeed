
from flask import Flask,render_template
#from flaskext.mysql import MySQL
from flask import jsonify
from flask import make_response
from flask import request
import json
import requests
import sys
import MySQLdb
import time
print("V1")

app = Flask(__name__)

# session = { 'db':"sssss" }

import peewee
from peewee import *
import MySQLdb

db = MySQLDatabase('KittyFeed', host='mysql', user='root', passwd='mypassword')

class Food(peewee.Model):
    TIME = peewee.TextField()
    Info = peewee.TextField()

    class Meta:
        database = db



class Horario(peewee.Model):
    TIME = peewee.TextField()
    INFO = peewee.TextField(default="err")

    class Meta:
        database = db




Horario.create_table()
Food.create_table()

@app.before_first_request
def function_to_run_only_once():


    food = Food(TIME="me", Info='Peewee is cool')
    food.save()
    for food in Food.filter(TIME="me"):
        print (food.Info,file=sys.stderr)
    return

@app.route("/")
def hello():

    teste=2
    conn = MySQLdb.connect(host='mysql', user='root', passwd='mypassword', db='menu')
    cur = conn.cursor()
    command = cur.execute('SELECT * FROM fish')
    results = cur.fetchall()
    conn.close()
    print("hello")
    texto=("{} --> {} @ {}€ \n").format(results[teste][0],results[teste][1],results[teste][2])
    return texto



@app.errorhandler(404)
def resource_not_found(error):
    return make_response(jsonify({'error':'Resource not found!'}), 404)

@app.errorhandler(400)
def invalid_request(error):
    return make_response(jsonify({'error': 'Bad Request'}), 400)
# nova versao ---------------



class tempo:
    def __init__(self):
        tempo.horas = []
        tempo.min = []

@app.route('/Feed',  methods=['GET','POST'])
def handler():
    # db = session.get('db', None)
    db = MySQLDatabase('KittyFeed', host='mysql', user='root', passwd='mypassword')
    class Horario(peewee.Model):
        TIME = peewee.TextField()
        Enable = peewee.TextField()

        class Meta:
            database = db



    if request.method == 'POST':
        req_data = request.get_json()
        feed = req_data['feed']
        # repetir = req_data['repetir']
        # tempo.horas = req_data['horas']
        # min = req_data['min']
        if feed == 'true':
            try:
                res = requests.post('http://172.29.1.229:5002/open', json={"open": "true"})
            except:
                print ("\n chegou \n",file=sys.stderr)

            food = Food(TIME=time.strftime('%l:%M%p %Z on %b %d, %Y'), Info=feed)
            food.save()

                # print("Erro a escrever dados na db",file=sys.stderr)

        return ("TOOOP repetir: {} \n")
                # "tempo:{}:{}".format(repetir,tempo.horas[0],min[0]))

    return("OPS deu errro {} ")
if __name__ == "__main__":
    app.run(host='0.0.0.0', port=5000, debug=True)



#  curl -i -H "Content-Type: application/json" -X POST -d '{"username":"mahesh@rocks", "email": "mahesh99@gmail.com","password": "mahesh123", "name":"Mahesh", "id":"20" }' http://localhost:5000/api/v1/users
