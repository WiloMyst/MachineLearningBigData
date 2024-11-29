import pandas as pd
import random
import math
import matplotlib.pyplot as plt
 
documentObject = pd.read_csv(r"D:\我的文档w\学习\大学学习\毕业设计\毕设文件\code\data.csv")
documentLst = documentObject.values.tolist()

dataLength = len(documentLst)
#k为聚类中心点个数
k = 3
#iteraions为起始的迭代量
iterations = 0
#maxIteraions表示最大迭代次数
maxIteraions = 400
#centre用来保存中心点的坐标,随机出k个样本数据作为中心点
centre = []
for i in range(k):
    centre.append([0]*2)
for i in range(k):
    tempIndex = random.randint(0, dataLength - 1)
    centre[i][0] = documentLst[tempIndex][0]
    centre[i][1] = documentLst[tempIndex][1]
print("初始中心为:", centre)
#disRecord用于记录每一个样本点里中心点的距离
disRecord = []
for i in range(dataLength):
    disRecord.append([0]*3)
#定义distance函数计算两点间距离
def distance(samples, centres):
    return math.sqrt((samples[0] - centres[0])**2 + (samples[1] - centres[1])**2)
 
finalClusters = []
for i in range(k):
    finalClusters.append([])

#进行k-means主体
while iterations < maxIteraions:
    #用于记录新的k个样本中心点的坐标
    new_centres = []
    for i in range(k):
        new_centres.append([0]*2)
    # cluster用于记录聚类结果[[], [], []]
    clusters = []
    for i in range(k):
        clusters.append([])
    for i in range(dataLength):
        for j in range(k):
            disRecord[i][j] = distance(documentLst[i], centre[j])
    #将某一个样本点的坐标添加至cluster列表中
    for i in range(dataLength):
        index = disRecord[i].index(min(disRecord[i]))
        clusters[index].append(i)
        new_centres[index][0] += documentLst[i][0]
        new_centres[index][1] += documentLst[i][1]
    #创建用于迭代的中心点数组
    temp_centre = []
    for i in range(k):
        temp_centre.append([0]*2)
    for i in range(k):
        temp_centre[i][0] = (new_centres[i][0])/len(clusters[i])
        temp_centre[i][1] = (new_centres[i][1])/len(clusters[i])
    if(temp_centre == centre):
        break;
    else:
        centre = temp_centre
        iterations += 1
        finalClusters = clusters
print("聚类中心为:", centre)
#print("分类情况为:", finalClusters)

plt.rcParams['font.sans-serif'] = ['SimHei']
plt.rcParams['axes.unicode_minus'] = False
colors = ['red', 'yellow', 'aqua']
for i, j in zip(range(k), colors):
    for index in finalClusters[i]:
        plt.scatter(x = documentLst[index][0], y = documentLst[index][1], c=j)
plt.xlabel('睡眠时长')
plt.ylabel('睡眠分数')
plt.show()
