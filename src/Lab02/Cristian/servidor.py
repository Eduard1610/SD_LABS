import socket
import datetime

# funcion para iniciar el servidor de reloj
def iniciar_servidor():
    
    s = socket.socket()
    print("Socket creado exitosamente!")

    puerto = 8000
    s.bind(('', puerto))

    # escuchar las solicitudes
    s.listen(5)
    print("Socket escuchando en el puerto ", puerto)

# funcion principal