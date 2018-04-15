

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
import sched
from datetime import datetime
from datetime import timedelta
import threading
print("V1")

app = Flask(__name__)


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
    HORAS = peewee.TextField()
    MIN = peewee.TextField()
    STATE = peewee.TextField()
    # MIN = peewee.IntegerField()

    class Meta:
        database = db




Horario.create_table()
Food.create_table()




def trabalhador(n):
    time.sleep(n)
    print('Dar comdia', file=sys.stderr)
    try:
        res = requests.post('http://172.29.9.108:5002/open', json={"open": "true"})
    except:
        print("\n Erro no envio para o PI!!!!!!!!!!!!!\n", file=sys.stderr)

    food = Food(TIME=time.strftime('%l:%M%p %Z on %b %d, %Y'), Info=feed)
    food.save()
    print('Dar comdia',file=sys.stderr)  # b will never complete because it is waiting on a.
    return 5

def master(tempoo): # calcula quanto tempo tem de esperar (tem de receber um vector com dois valors)

    d = timedelta(hours=int(tempoo[0]),minutes=int(tempoo[1]))
    sec = d.total_seconds()
    print(time.strftime('%H'), file=sys.stderr)
    print(time.strftime('%M'), file=sys.stderr)
    horas_s= (int(time.strftime('%H'))+1)*60*60
    mins_s = int(time.strftime('%M'))*60

    agora = horas_s+mins_s

    print(horas_s, file=sys.stderr)
    print(mins_s, file=sys.stderr)

    print((sec-agora), file=sys.stderr)
    #k = time.sleep(d.total_seconds()-time.time())
    return (abs(sec-agora)*0.25)


@app.before_first_request
def function_to_run_only_once():

    horas = Horario.filter(HORAS=time.strftime('%H'),MIN=time.strftime('%M'),STATE='false')
    if Horario.HORAS:
        for tempo in Horario.filter(HORAS="10"):
            print(tempo.HORAS, file=sys.stderr)

    # for food in Food.filter(TIME="me"):
    #     print (food.Info,file=sys.stderr)
    # return

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


@app.route('/config')
def db_dumps():
    text= []
    for food in Horario:
        text=[food.HORAS]+text[:]

    return(make_response(jsonify({'TIME': text}),200))
@app.route('/set',methods=['POST'])
def ler_dados():
    if request.method == 'POST':
        req_data = request.get_json()
        horas = req_data['horas']
        #mins = req_data['mins']
        reset = req_data['reset']
        if reset == 'true':

            query = Horario.delete()
            query.execute()
        RELOGIO=[]
        for i in range(len(horas)):
            dados = horas[i].split(':')
            tempoo = Horario(HORAS=dados[0],MIN=dados[1],STATE='false')
            # print(int(dados), file=sys.stderr)
            algoo = master(dados)
            temp_1 = threading.Thread(target=trabalhador,args=(algoo,))
            RELOGIO.append(temp_1)
            temp_1.start()

            tempoo.save()
        now = datetime.now()

        run_at = now + timedelta(hours=3)
        delay = (run_at - now).total_seconds()
        # threading.Timer(delay, self.update).start()

            #print("{} - {}:{}".format(i,horas[i],mins[i]),file=sys.stderr)
        #make_response(jsonify({'horas': horas[:]}),200)
    return ('suup')

@app.route('/Feed',  methods=['POST'])
def handler():


    if request.method == 'POST':
        req_data = request.get_json()
        feed = req_data['feed']
        if feed == 'true':
            try:
                res = requests.post('http://172.29.9.108:5002/open', json={"open": "true"})
            except:
                print ("\n chegou \n",file=sys.stderr)

            food = Food(TIME=time.strftime('%l:%M%p %Z on %b %d, %Y'), Info=feed)
            food.save()

                # print("Erro a escrever dados na db",file=sys.stderr)

        return ("TOOOP repetir: {} \n")
                # "tempo:{}:{}".format(repetir,tempo.horas[0],min[0]))

    return("OPS deu errro {} ")



    # print('\nDeitar comida: {}\n'.format(Horario.HORAS),file=sys.stderr)

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=5000, debug=True)


#  curl -i -H "Content-Type: application/json" -X POST -d '{"username":"mahesh@rocks", "email": "mahesh99@gmail.com","password": "mahesh123", "name":"Mahesh", "id":"20" }' http://localhost:5000/api/v1/users
