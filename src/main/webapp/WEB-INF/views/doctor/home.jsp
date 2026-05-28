<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pacifico.sistemacitas.model.Cita" %>

<html>
<head>
    <title>Panel Doctor</title>

    <style>
        body {
            font-family: Arial;
            background: #f4f6f9;
        }

        .header {
            background: #1565c0;
            color: white;
            padding: 15px;
            display: flex;
            justify-content: space-between;
        }

        .container {
            padding: 20px;
        }

        .card {
            background: white;
            padding: 15px;
            margin-bottom: 10px;
            border-radius: 10px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }

        .badge {
            padding: 4px 10px;
            border-radius: 5px;
            color: white;
            font-size: 12px;
        }

        .PENDIENTE { background: orange; }
        .ATENDIDA { background: green; }
        .CANCELADA { background: red; }

        .btn {
            padding: 6px 10px;
            text-decoration: none;
            border-radius: 6px;
            color: white;
            margin-right: 5px;
        }

        .atender { background: green; }
        .cancelar { background: red; }
    </style>
</head>

<body>

<div class="header">
    <h2>👨‍⚕️ Portal Doctor</h2>

    <div>
        <a href="${pageContext.request.contextPath}/home" style="color:white;">
            🏠 Inicio
        </a>
        |
        <a href="${pageContext.request.contextPath}/logout" style="color:white;">
            🚪 Salir
        </a>
    </div>
</div>

<div class="container">

    <h3>📅 Mis Citas</h3>

    <%
        List<Cita> citas = (List<Cita>) request.getAttribute("citas");

        if (citas != null && !citas.isEmpty()) {
            for (Cita c : citas) {
    %>

    <div class="card">
        <h4>Paciente: <%= c.getPaciente() %></h4>
        <p>DNI: <%= c.getDni() %></p>
        <p>📅 <%= c.getFecha() %> - ⏰ <%= c.getHora() %></p>

        <span class="badge <%= c.getEstado() %>">
            <%= c.getEstado() %>
        </span>

        <br><br>

        <a class="btn atender"
           href="${pageContext.request.contextPath}/doctor/atender?id=<%= c.getId() %>">
            ✔ Atender
        </a>

        <a class="btn cancelar"
           href="${pageContext.request.contextPath}/doctor/cancelar?id=<%= c.getId() %>">
            ❌ Cancelar
        </a>
    </div>

    <%
        }
    } else {
    %>

    <p>No hay citas disponibles</p>

    <%
        }
    %>

</div>

</body>
</html>
``