from flask import Flask
from flask import render_template, make_response, request, json
import json
import connexion
import database
import datetime
from flask_cors import CORS

app = Flask(__name__)
CORS(app, supports_credentials=True)

def register(username, password, firstname, lastname, weight, age, gender):
    
    if username == None or password == None or firstname == None or lastname == None:
        return False

    db = database.DB()

    db.create_user(username, password, firstname, lastname, weight, age, gender)

    return True

def login(username, password):
    
    if username == None or password == None:
        return False

    db = database.DB()

    return db.login(username, password)

def add_training(username, start_timestamp, stop_timestamp, total_count, success_count, calories_count):
    db = database.DB()

    return db.add_training(username, start_timestamp, stop_timestamp, total_count, success_count, calories_count)

def total_count(username):
    db = database.DB()

    return db.total_count(username)

def weight(username):
    db = database.DB()

    return db.weight(username)

@app.route('/')
def home():
    return render_template('home.html')

@app.route('/api/register', methods = ['POST'])
def register_api():
    resp = make_response()

    # Verificare campuri
    if 'username' not in request.json or 'password' not in request.json or 'lastname' not in request.json or 'firstname' not in request.json or 'gender' not in request.json:
        return resp, 400
   
    # Se incearca inregistrarea
    rc = register(
            username = request.json['username'],
            password = request.json['password'],
            firstname = request.json['firstname'],
            lastname = request.json['lastname'],
            weight = int(request.json['weight']),
            age = int(request.json['age']),
            gender = request.json['gender']
        )

    if not rc:
        return resp, 401
    
    resp = app.response_class(
        response=json.dumps(
            {
                'username' : request.json['username']
            }
            ),
        mimetype='application/json'
    )

    return resp, 200

@app.route('/api/login', methods = ['POST'])
def login_api():
    resp = make_response()

    rc = login(
        username = request.json['username'],
        password = request.json['password']
    )

    if not rc:
        return resp, 401

    return resp, 200

@app.route('/api/training', methods = ['POST'])
def add_training_api():
    resp = make_response()

    rc = add_training(
        username = request.json['username'],
        start_timestamp = request.json['start_timestamp'],
        stop_timestamp = request.json['stop_timestamp'],
        total_count = request.json['total_count'],
        success_count = request.json['success_count'],
        calories_count = request.json['calories_count']
    )

    if not rc:
        return resp, 401

    return resp, 200

@app.route('/api/totalcount', methods = ['POST'])
def get_total_count():
    resp = make_response()

    count = total_count(
        username = request.json['username']
    )

    resp = app.response_class(
        response=json.dumps(
            {
                'count' : count
            }
            ),
        mimetype='application/json'
    )
    print(resp.json)
    return resp, 200

@app.route('/api/weight', methods = ['POST'])
def get_weight():
    resp = make_response()

    w = weight(
        username = request.json['username']
    )

    resp = app.response_class(
        response=json.dumps(
            {
                'weight' : w
            }
            ),
        mimetype='application/json'
    )
    print(resp.json)
    return resp, 200

if __name__ == '__main__':
    app.run(host = '10.1.1.69', port = '4242', debug = True)
