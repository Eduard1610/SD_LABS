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
    tiempo_servidor = parser.parse(s.recv(1024).decode())
    tiempo_respuesta = timer()
    tiempo_actual = datetime.datetime.now()

    # resultado de la sincronizacion
    print(f"\nEl tiempo devuelto por el servidor: {tiempo_servidor}")
    # calculo del retardo
    retardo = tiempo_respuesta - tiempo_solicitud
    print(f"\nEl retardo del proceso: {retardo} s.")
    print(f"El tiempo actual del cliente: {tiempo_actual}")
    # calculo de la sincronizacion
    tiempo_del_cliente = tiempo_servidor + datetime.timedelta(seconds = retardo / 2)
    print(f"El tiempo sincronizado del cliente: {tiempo_del_cliente}")
    # error de sincronizacion (diferencia entre el tiempo actual y el tiempo sincronizado)
    error = tiempo_actual - tiempo_del_cliente
    print(f"\nEl error de sincronizacion: {error.total_seconds()} s.\n")

    # cerrar la conexion
    s.close()

# funcion principal
if __name__ == '__main__':
    sincronizar_tiempo()