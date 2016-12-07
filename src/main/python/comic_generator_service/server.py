from flask import Flask
from random import randint

import requests

app = Flask(__name__)


@app.route('/comic', methods=["GET"])
def hello_world():
    first = "http://xkcd.com/"
    random_number = randint(1, 1500)
    third = "/info.0.json"
    route = first + str(random_number) + third
    print(route)
    json = requests.get(route).json()
    print(json)
    return json["img"]


if __name__ == '__main__':
    app.run(host="0.0.0.0", port=60002)
