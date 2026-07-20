<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Clínica Pacífico</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>

<body>

<div class="container">

    <img src="${pageContext.request.contextPath}/images/images.png" class="logo">

    <h2>Clínica Pacífico</h2>
    <p>Reserva tu cita médica</p>

    <div class="card">

        <form action="${pageContext.request.contextPath}/cita" method="post">

            <div class="form-group">
                <label>Nombre completo</label>
                <div class="input-box">
                    <span class="icon">👤</span>
                    <input type="text" name="nombre" placeholder="Ingrese su nombre" required>
                </div>
            </div>

            <div class="form-group">
                <label>Fecha de la cita</label>
                <div class="input-box">
                    <span class="icon">📅</span>
                    <input type="date" name="fecha" required>
                </div>
            </div>

            <button type="submit">Reservar Cita</button>

            <a href="${pageContext.request.contextPath}/reservar-cita">
                Reservar Cita
            </a>

        </form>
    </div>
</div>