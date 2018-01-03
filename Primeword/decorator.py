from functools import wraps

import jwt
from datetime import datetime
from rest_framework.response import Response

from Primeword.settings import SECRET_KEY
from users.models import UserVO


def jwt_security(func):
    @wraps(func)
    def decorator(request, *args, **kwargs):
        print("%s %s" % (func.__name__, "before"))
        token = request.META.get('HTTP_X_AUTH_TOKEN')
        print("-------token : ", token)
        try:
            # token 이 jwt 형식이 맞으면 토큰정보 출력
            data = jwt.decode(token, SECRET_KEY, algorithms=['HS256'])
            # token 에서 exp 기간이 지났으면 expired message
            exp = data['exp']
            # exp = 1514354948
            print("exp : ", exp)
            print("now : ", datetime.now().timestamp())
            if exp < datetime.now().timestamp():
                error_message = {
                    'message': '인증이 만료되었습니다. 다시로그인해 주세요.',
                }
                return Response(error_message, status=401, content_type="application/json")

            # token 에 해당하는 유저가 있는지 확인
            # sid = data['sid']
            # social = data['social']
            # user = UserVO.objects.get(sid=sid, social=social)
        except:
            # kwargs['token'] = 'invalid jwt token'
            # print(kwargs)
            message = {
                "message": '유효하지 않은 token 입니다.'
            }
            return Response(message)
            # raise Exception('no access token')
        return func(request, *args, **kwargs)

    return decorator
