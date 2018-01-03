import jwt
import requests
from datetime import timedelta, datetime
from django.utils.decorators import method_decorator
from rest_framework.generics import ListCreateAPIView, RetrieveUpdateDestroyAPIView, GenericAPIView, ListAPIView, CreateAPIView
from rest_framework.response import Response

from Primeword.decorator import jwt_security
from Primeword.settings import SECRET_KEY
from users.models import UserVO
from users.serializer import UsersSerializer, AuthsSerializer
# Create your views here.

# @method_decorator(jwt_security, name='get')
class UsersList(ListCreateAPIView):
    """
    get: 전체 회원정보 요청
    post: 소셜로그인정보 저장(회원가입 X)
    """
    queryset = UserVO.objects.all()
    serializer_class = UsersSerializer


# @method_decorator(jwt_security, name='get')
# @method_decorator(jwt_security, name='put')
# @method_decorator(jwt_security, name='delete')
class UsersDetail(RetrieveUpdateDestroyAPIView):
    """
    get: 회원정보 요청
    put: 회원정보 수정
    delete: 회원 삭제
    """
    queryset = UserVO.objects.all()
    serializer_class = UsersSerializer



# class AuthsApi(GenericAPIView):
#     """
#     get: 새 jwt token 요청
#     post: 소셜로그인 및 회원가입 요청
#     """
#     serializer_class = AuthsSerializer
#
#     def get(self, request, *args, **kwagrs):
#         token = request.META.get('HTTP_X_AUTH_TOKEN')
#         print("-------token : ", token)
#         try:
#             # token 이 jwt 형식이 맞으면 토큰정보 출력
#             data = jwt.decode(token, SECRET_KEY, algorithms=['HS256'])
#             # token 에 해당하는 유저가 있는지 확인
#             sid = data['sid']
#             social = data['social']
#             print(3)
#             user = UserVO.objects.get(sid=sid, social=social)
#             # 새로 jwt token 발급?
#             print(4)
#         except:
#             message = {
#                 "message": 'invalid jwt token'
#             }
#             return Response(message, status=401, content_type="application/json")
#
#         return Response(data, status=200, content_type="application/json")
#
#
#     def post(self, request, *args, **kwargs):
#
#         serializer = AuthsSerializer(data=self.request.data)
#         access_token = request.data['access_token']
#         header = {
#             "Authorization": "Bearer " + access_token
#         }
#         url = "https://openapi.naver.com/v1/nid/me"
#         r = requests.request('GET', url, headers=header)
#         # print("r =============", r.json())
#         if r.json()['resultcode'] == '024':
#             error_message = {
#                 'message': 'Naver Authentication failed',
#             }
#             return Response(error_message, status=401, content_type="application/json")
#         sid = r.json()['response']['id']
#         # social = 'naver'
#         social = request.data['social']
#         try:
#             # 네이버에 받아온 sid 를통해 가입여부를 확인한다.
#             user = UserVO.objects.get(sid=sid, social=social)
#             print("====================== 1 회원가입 되어있음 ======================")
#             # jwt token 으로 만들어 반환
#             payload = {
#                 # 'aud': user.id,
#                 'sid': user.sid,
#                 'social': user.social,
#                 'exp': datetime.now() + timedelta(days=1)
#             }
#             jwt_token = {'token': jwt.encode(payload, SECRET_KEY, algorithm='HS256')}
#             return Response(jwt_token, status=200, content_type="application/json")
#         except UserVO.DoesNotExist:
#             # 가입하지 않았으면 새로 user 테이블에 추가
#             user_data = {
#                 'social': social,
#                 'sid': sid
#                 # 'exp': datetime.now() + timedelta(days=1)
#             }
#             joinUrl = 'http://' + request.META['HTTP_HOST'] + '/users/'
#             print(joinUrl)
#             r2 = requests.request('POST', joinUrl, json=user_data)
#             if r2.status_code != 201:
#                 return Response({'Error': "Invalid sid/social"}, status="400")
#             # if user:
#
#             user = UserVO.objects.get(sid=sid, social=social)
#             print("====================== 2 회원가입 새로함======================")
#             # jwt token 으로 만들어 반환
#             payload = {
#                 # 'aud': user.id,
#                 'sid': user.sid,
#                 'social': user.social,
#                 'exp': datetime.now() + timedelta(days=1)
#             }
#             jwt_token = {'token': jwt.encode(payload, SECRET_KEY, algorithm='HS256')}
#             return Response(jwt_token, status=200, content_type="application/json")
