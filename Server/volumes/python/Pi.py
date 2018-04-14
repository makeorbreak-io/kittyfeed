
from flask import Flask,render_template
#from flaskext.mysql import MySQL
from flask import jsonify
from flask import make_response
from flask import request
import sys
import requests

print("V1")

app = Flask(__name__)



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

@app.route('/open', methods=['GET', 'POST'])
def add_message():
    content = request.get_json()
    cont = content['open']

    #     print("dar comida")
    print(cont, file=sys.stderr)
    return ("TOOOP")


if __name__ == "__main__":
    app.run(host='0.0.0.0', port=5002, debug=True)

