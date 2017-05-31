# -*- coding: utf-8 -*-
import sys
import os
from os import listdir
import glob
import fnmatch

contadorPEK = 0
contadorCFA = 0
contadorAVI = 0

TRACEBACKPRINT = True
DELETING = False
PATH = 'D:\cap17 26092016\proyecto 17\\'

for root, dirs, files in os.walk(PATH):
    for directorio in dirs:
        if directorio.endswith(".PRV"):
            print directorio
            list = os.listdir(os.path.join(root, directorio))
            countfiles = len(list)
            print countfiles
    for file in files:
        if file.endswith(".pek"):
            contadorPEK += 1
            if TRACEBACKPRINT:
                print file
            if DELETING:
                os.remove(os.path.join(root, file))

        if file.endswith(".cfa"):
            contadorCFA += 1
            if TRACEBACKPRINT:
                print file
            if DELETING:
                os.remove(os.path.join(root, file))
        if fnmatch.fnmatch(file, 'Rendered - *.AVI'):
            contadorAVI += 1
            if TRACEBACKPRINT:
                print file
            if DELETING:
                os.remove(os.path.join(root, file))

print '\n\n #### DATOS ####'
print str(contadorAVI) + " archivos AVI eliminados"
print str(contadorPEK) + " archivos PEK eliminados"
print str(contadorCFA) + " archivos CFA eliminados"

raw_input("\nPresiona una tecla para cerrar...")
exit(0)

'''
listFiles = glob.glob('*.pek')
for file in listFiles:
    print file


for item in test:
    if item.endswith(".pek"):
        print os.path.join( directory, item )
        os.remove( os.path.join( directory, item ) )
'''