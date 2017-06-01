# -*- coding: utf-8 -*-
import os
import fnmatch

TRACEBACKPRINT = True
DELETING = True
PATH = 'D:\\test\\'

def main():
    print ' *** Adobe Premiere Pro Cleaner ***' \
          '\nClean the Premiere Projects before backuping or moving it anywhere deleting unnecessary preview files, and audio / video renders for real time editing: .CFA, .PEK, .AVI.' \
          '' \
          '\nInput example: D:\\test\\'
    input_path = raw_input("Introduce ruta a escanear: ")

    PATH = input_path

    contadorPEK = 0
    contadorCFA = 0
    contadorAVI = 0

    PEKsize = 0
    CFAsize = 0
    AVIsize = 0

    for root, dirs, files in os.walk(PATH):
        for directory in dirs:
            if directory.endswith(".PRV"):
                list = os.listdir(os.path.join(root, directory))
                countfiles = len(list)
                print directory + ', archivos: ' + str(countfiles)
                if DELETING:
                    if (countfiles == 0):
                        #print 'tiene 0 archivos'
                        os.rmdir(os.path.join(root, directory))
        for file in files:

            if file.endswith(".pek"):
                statinfo = os.stat(os.path.join(root, file))
                contadorPEK += 1
                PEKsize += statinfo.st_size
                if TRACEBACKPRINT:
                    print '\t' + file + ' - ' + str(statinfo.st_size) + ' Bytes'
                if DELETING:
                    os.remove(os.path.join(root, file))

            if file.endswith(".cfa"):
                statinfo = os.stat(os.path.join(root, file))
                contadorCFA += 1
                CFAsize += statinfo.st_size
                if TRACEBACKPRINT:
                    print '\t' + file + ' - ' + str(statinfo.st_size) + ' Bytes'
                if DELETING:
                    os.remove(os.path.join(root, file))
            if fnmatch.fnmatch(file, 'Rendered - *.AVI'):
                statinfo = os.stat(os.path.join(root, file))
                contadorAVI += 1
                AVIsize += statinfo.st_size
                if TRACEBACKPRINT:
                    print '\t' + file + ' - ' + str(statinfo.st_size) + ' Bytes'
                if DELETING:
                    os.remove(os.path.join(root, file))

    print '\n\n #### DATOS ####'
    print str(contadorAVI) + " archivos AVI eliminados" + ' - ' + str(AVIsize) + ' Bytes = ' + str((AVIsize*1.0)/1000000000.0) + ' GB'
    print str(contadorPEK) + " archivos PEK eliminados" + ' - ' + str(PEKsize) + ' Bytes = ' + str((PEKsize*1.0)/1000000000.0) + ' GB'
    print str(contadorCFA) + " archivos CFA eliminados" + ' - ' + str(CFAsize) + ' Bytes = ' + str((CFAsize*1.0)/1000000000.0) + ' GB'
    totalSize = AVIsize + PEKsize + CFAsize
    print 'Espacio total liberado: ' + str(totalSize) + ' Bytes = ' + str((totalSize*1.0)/1000000000.0) + ' GB'

    raw_input("\nPresiona una tecla para cerrar...")
    exit(0)

if __name__ == '__main__':
    main()
