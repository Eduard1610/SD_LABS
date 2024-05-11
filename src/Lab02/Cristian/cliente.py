import socket 
import datetime
from dateutil import parser
from timeit import default_timer as timer

# funcion para sincronizar el tiempo con el servidor
def sincronizar_tiempo():

    s = socket.socket()

    puerto = 8000
    # conexion con el servidor
    s.connect(('localhost', puerto))

    tiempo_solicitud = timer()

    # recibir la hora del servidor
    tiempo_solicitud = parser.parse(s.recv(1024).decode())
    tiempo_respuesta = datetime.datetime.now()

# funcion principal
if __name__ == '__main__':
    sincronizar_tiempo()