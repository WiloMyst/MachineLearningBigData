# 爬取2022年的睡眠数据
import requests
import pandas as pd
import numpy as np
import re

result = []
url = "https://sleep.flow-prd.api.polar.com/api/sleep/nights/nearby"
headers = {
    "authority": "sleep.flow-prd.api.polar.com",
    "accept": "application/json, text/javascript, */*; q=0.01",
    "accept-encoding": "gzip, deflate, br",
    "accept-language": "zh-CN,zh;q=0.9",
    "content-type": "application/json",
    "cookie": "POLAR_SSO=1; POLAR_SESSION=eyJhbGciOiJSUzI1NiJ9.eyJzY29wZXMiOlsiUE9MQVJfU1NPIl0sImV4cCI6MTY0Mjg1Njc2NywiaWF0IjoxNjExMzIwNzY3LCJ1c2VySWQiOjQzMzg3MTk0LCJ1dWlkIjoiYWQ2MGRhZDEtN2VlYi00YWNiLWI2ZmEtMjE4ZTE5MTFlNTM1In0.xwtiI0Irb-1JdPNSKdbbutzNsVCqlLGkPBfT-FQ5RrHWiTmJLmtkKEAbi5cGj5NDG8l1W45hh6meAv4HOkbTopqJm6SJYzWazv0i0kN5vfsKkYWoHHg8jFDyAOR_QkUra5SFEPKvA3i-N9tFxP_z3HWDQX4Lvw78eLxpkRaJmhg9V7PcgxAPK5DNfDMi6TIEdIGA9zyYpVznJw6mW370tOSUVtc10SY2Ynfk-DUBcDalw2ewnMQ3DAI-U7HrZ7G_frBJxxL0QTnzlJ0vwCChR4Krl9ikk0zb8v8hlCZFjEtAW858oli_2Sxo0X_E3BN6zVvrnr1cH_iqjh0wBcEt7g; JSESSIONID=09C074C68BA81D445CC6592BCB47A8B6; OptanonAlertBoxClosed=2021-01-22T13:08:07.595Z; OptanonConsent=isIABGlobal=false&datestamp=Fri+Jan+22+2021+21%3A08%3A07+GMT%2B0800+(%E4%B8%AD%E5%9B%BD%E6%A0%87%E5%87%86%E6%97%B6%E9%97%B4)&version=6.6.0&hosts=&landingPath=NotLandingPage&groups=C0004%3A1%2CC0001%3A1%2CC0002%3A1%2CC0003%3A1&AwaitingReconsent=false; _ga=GA1.2.557689735.1611320888; _gid=GA1.2.1639346864.1611320888; _gat=1; _dc_gtm_UA-66185860-2=1",
    "origin": "https://flow.polar.com",
    "user-agent": "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36",
    "x-requested-with": "XMLHttpRequest"
}

# 整理数据
datetime_list = list(pd.date_range(start='2022-01-01',end='2022-12-31'))
datetime_list =[i.strftime("%Y-%m-%d") for i in datetime_list]
for datetime_ in datetime_list:
    data = {"date":datetime_}
    r = requests.get(url,headers = headers,params=data)
    content = r.json()
    date = content['previousNights'][0]['nightSleep']['night']
    sleep_start_time = content['previousNights'][0]['nightSleep']['sleepStartTime']
    sleep_end_time = content['previousNights'][0]['nightSleep']['sleepEndTime']
    sleep_time = content['previousNights'][0]['sleepEvaluationData']['asleep']
    efficiencyPercent = content['previousNights'][0]['sleepEvaluationData']['efficiencyPercent']
    continuityIndex = content['previousNights'][0]['sleepEvaluationData']['continuityIndex']
    result.append([date,sleep_start_time,sleep_end_time,sleep_time,efficiencyPercent,continuityIndex])
    print(date,sleep_start_time,sleep_end_time,sleep_time,efficiencyPercent,continuityIndex)

# 保存数据
sleep_df = pd.DataFrame(result)
sleep_df.columns = ['date','sleep_start_time','sleep_end_time','sleep_time','efficiencyPercent','continuityIndex']
sleep_df.to_csv("datas.csv")
