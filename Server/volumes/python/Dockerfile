from python:3
WORKDIR /usr/src/app
volume /usr/src/app
COPY requirements.txt ./
#run mkdir /myvol
#COPY requirements.txt /usr/src/app/myvol/
#copy script.py /usr/src/app/myvol/
RUN pip install --no-cache-dir -r requirements.txt

CMD [ "python", "/usr/src/app/script.py" ]
