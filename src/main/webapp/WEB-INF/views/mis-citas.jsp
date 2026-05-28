<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*" %>

<html>
<head>
  <title>Mis Citas</title>

  <link rel="stylesheet"
        href="${pageContext.request.contextPath}/css/home.css">

  <style>

    body {
      font-family: Arial;
      background: #f4f6f9;
    }

    .container {
      padding: 30px;
    }

    h1 {
      text-align: center;
      margin-bottom: 30px;
    }

    .grid {
      display: grid;
      grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
      gap: 20px;
    }

    .card {
      background: white;
      padding: 20px;
      border-radius: 15px;
      box-shadow: 0 5px 15px rgba(0,0,0,0.1);
      border-left: 6px solid #1e88e5;
    }

    .card h3 {
      margin: 0 0 10px;
      color: #1e88e5;
    }

    .estado {
      padding: 5px 10px;
      border-radius: 10px;
      font-size: 12px;
      color: white;
      display: inline-block;
    }

    .PENDIENTE { background: orange; }
    .CONFIRMADA { background: green; }
    .CANCELADA { background: red; }
    .ATENDIDA { background: blue; }

    .top {
      display:flex;
      justify-content: space-between;
      margin-bottom: 20px;
    }

    a {
      text-decoration: none;
    }

    .btn {
      background: #1e88e5;
      color: white;
      padding: 10px 15px;
      border-radius: 10px;
    }

  </style>

</head>

<body>

<div class="container">

  <div class="top">
    <a class="btn"
       href="${pageContext.request.contextPath}/home">
      ← Inicio
    </a>

    <a class="btn"
       href="${pageContext.request.contextPath}/reservar-cita">
      + Nueva Cita
    </a>
  </div>

  <h1>Mis Citas Médicas</h1>

  <div class="grid">

    <%
      List<Map<String,String>> citas =
              (List<Map<String,String>>) request.getAttribute("citas");

      if (citas != null && !citas.isEmpty()) {

        for (Map<String,String> c : citas) {
    %>

    <div class="card">

      <h3><%= c.get("medico") %></h3>

      <p><b>Fecha:</b> <%= c.get("fecha") %></p>
      <p><b>Hora:</b> <%= c.get("hora") %></p>

      <span class="estado <%= c.get("estado") %>">
                <%= c.get("estado") %>
            </span>
      <a href="${pageContext.request.contextPath}/cancelar-cita?id=<%= c.get("id") %>"
         style="
       display:inline-block;
       margin-top:10px;
       background:#e53935;
       color:white;
       padding:8px 12px;
       border-radius:8px;
       text-decoration:none;">
        Cancelar cita
      </a>

    </div>

    <%
      }
    } else {
    %>

    <p style="text-align:center;">
      No tienes citas registradas
    </p>

    <%
      }
    %>

  </div>

</div>

</body>
</html>
