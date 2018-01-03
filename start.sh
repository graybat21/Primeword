#!/bin/sh
pip install -r requirements.txt
python manage.py makemigrations
python manage.py migrate
gunicorn --workers=3 --bind 0:8000 --access-logfile /var/log/gunicorn/gunicorn-access.log --error-logfile /var/log/gunicorn/gunicorn-error.log Primeword.wsgi
exit 0
