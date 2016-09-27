#!/usr/bin/python2.7
#-*- coding: utf-8 -*-

import random, string

mapSize = raw_input('Enter a size for the map: ')



def random_generator(size=int(mapSize), chars=" "+" "+" "+" "+" "+" "+" "+" "+" "+" "+" "+" "+"*"):
    return ''.join(random.choice(chars) for x in range(size))

newMap = open("map.txt", "w")
newMap.write(mapSize+"\n")
for nbLine in range(int(mapSize)):
    newLine = random_generator()
    newMap.write(newLine+"\n")
newMap.close()
