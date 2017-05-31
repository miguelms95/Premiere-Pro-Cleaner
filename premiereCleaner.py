# -*- coding: utf-8 -*-
import sys
import os
from os import listdir
import glob
import fnmatch



TRACEBACKPRINT = True
DELETING = True
#PATH = 'D:\cap17 26092016\proyecto 17\\'
PATH = 'D:\\test\\'

def main():
    print ' *** Adobe Premiere Pro Cleaner ***' \
          '\nClean the Premiere Projects before backuping or moving it anywhere deleting unnecessary preview files, and audio / video renders for real time editing: .CFA, .PEK, .AVI.' \
          '\nParameter' \
          '\nInput example: D:\\test\\ 0'
    input_path = raw_input("Introduce ruta a escanear: ")

    PATH = input_path

    contadorPEK = 0
    contadorCFA = 0
    contadorAVI = 0

    for root, dirs, files in os.walk(PATH):
        for directory in dirs:
            if directory.endswith(".PRV"):
                list = os.listdir(os.path.join(root, directory))
                countfiles = len(list)
                print directory + ', archivos: ' + str(countfiles)
                if DELETING:
                    if (countfiles == 0):
                        print 'tiene 0 archivos'
                        os.rmdir(os.path.join(root, directory))
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

if __name__ == '__main__':
    main()

'''
listFiles = glob.glob('*.pek')
for file in listFiles:
    print file


for item in test:
    if item.endswith(".pek"):
        print os.path.join( directory, item )
        os.remove( os.path.join( directory, item ) )
'''