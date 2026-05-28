<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Panel Administrador</title>

    <style>
        body {
            font-family: Arial;
            background: #f4f6f9;
        }

        .container {
            padding: 30px;
        }

        .card {
            background: white;
            padding: 20px;
            border-radius: 12px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            margin-bottom: 15px;
        }

        a {
            display: inline-block;
            margin-top: 10px;
            padding: 8px 12px;
            background: #1e88e5;
            color: white;
            text-decoration: none;
            border-radius: 8px;
        }
    </style>

    <div style="padding:15px; background:#1e88e5; color:white; display:flex; justify-content:space-between; align-items:center;">

        <h2>🛠 Panel Administrador</h2>

        <div>
            <a href="${pageContext.request.contextPath}/home"
               style="color:white; margin-right:15px; text-decoration:none;">
                🏠 Inicio
            </a>

            <a href="${pageContext.request.contextPath}/logout"
               style="color:white; text-decoration:none;">
                🚪 Salir
            </a>
        </div>

    </div>
</head>

<body>

<div class="container">

    <h1>🛠️ Panel Administrador</h1>

    <div class="card">
        <h3>👨‍⚕️ Gestión de Doctores</h3>
        <p>Administrar médicos del sistema</p>
        <a href="${pageContext.request.contextPath}/doctor/home">Ir</a>
    </div>

    <div class="card">
        <h3>📅 Gestión de Citas</h3>
        <p>Ver todas las citas del sistema</p>
        <a href="${pageContext.request.contextPath}/mis-citas">Ver</a>
    </div>

    <div class="card">
        <h3>👤 Usuarios</h3>
        <p>Administrar pacientes</p>
    </div>

</div>

</body>
</html>
