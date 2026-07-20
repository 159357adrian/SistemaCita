<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Clínica Pacífico</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
</head>

<body>

<!-- NAVBAR -->
<header class="navbar">
    <div class="logo">
        <img src="${pageContext.request.contextPath}/images/images.png">
        <h2>Clínica Pacífico</h2>
    </div>

    <nav>
        <a href="#inicio">Inicio</a>
        <a href="#servicios">Servicios</a>
        <a href="#nosotros">Nosotros</a>

        <a href="${pageContext.request.contextPath}/reservar-cita" class="btn-nav">
            Reservar Cita
        </a>

        <a href="${pageContext.request.contextPath}/mis-citas" class="btn-nav">
            Mis Citas
        </a>

        <%
            String nombre = (String) session.getAttribute("nombre");

            if (nombre == null) {
        %>

        <a href="${pageContext.request.contextPath}/login" class="btn-login">
            Login
        </a>

        <%
        } else {
        %>

        <span>👤 <%= nombre %></span>

        <a href="${pageContext.request.contextPath}/logout">
            Salir
        </a>

        <%
            }
        %>

    </nav>
</header>

<!-- HERO / SLIDER -->
<section id="inicio" class="hero">

    <div class="slider">
        <div class="slide active" style="background-image: url('${pageContext.request.contextPath}/images/image1.jpeg');"></div>
        <div class="slide" style="background-image: url('${pageContext.request.contextPath}/images/image2.jpeg');"></div>
        <div class="slide" style="background-image: url('${pageContext.request.contextPath}/images/image3.jpeg');"></div>
    </div>

    <div class="hero-text">
        <h1>Atención médica de calidad</h1>
        <p>Disponibles las 24 horas</p>

        <a href="${pageContext.request.contextPath}/reservar-cita">
            Reservar Cita
        </a>
    </div>

</section>

<!-- SERVICIOS -->
<section id="servicios" class="info">
    <h2>¿En qué podemos ayudarte hoy?</h2>
</section>

<section class="servicios">

    <div class="card">
        <h3>Medicina General</h3>
        <p>Atención integral para toda la familia</p>
    </div>

    <div class="card">
        <h3>Laboratorio</h3>
        <p>Análisis clínicos rápidos y confiables</p>
    </div>

    <div class="card">
        <h3>Emergencias</h3>
        <p>Atención inmediata las 24 horas</p>
    </div>

</section>

<!-- NOSOTROS -->
<section id="nosotros" class="nosotros">

    <h2>Sobre Nosotros</h2>

    <p class="desc">
        En Clínica Pacífico brindamos atención médica de calidad con profesionales altamente capacitados,
        tecnología moderna y compromiso con tu salud.
    </p>

    <div class="galeria">

        <div class="img-box">
            <img src="${pageContext.request.contextPath}/images/EquipoProfesional.jpeg">
            <div class="overlay">Equipo Médico Profesional</div>
        </div>

        <div class="img-box">
            <img src="${pageContext.request.contextPath}/images/Instalaciones.jpeg">
            <div class="overlay">Instalaciones Modernas</div>
        </div>

        <div class="img-box">
            <img src="${pageContext.request.contextPath}/images/AtenciónCalidad.jpeg">
            <div class="overlay">Atención de Calidad</div>
        </div>

    </div>

</section>

<!-- FOOTER -->
<footer class="footer">

    <h3>Contáctanos</h3>
    <p>Estamos para ayudarte las 24 horas</p>

    <div class="redes">

        <a href="#"><img src="${pageContext.request.contextPath}/images/Facebook.png"> Facebook</a>
        <a href="#"><img src="${pageContext.request.contextPath}/images/whatssap.png"> WhatsApp</a>
        <a href="#"><img src="${pageContext.request.contextPath}/images/Instagram.png"> Instagram</a>

    </div>

</footer>

<!-- BOTÓN WHATSAPP -->
<a href="https://wa.me/51945678243" class="whatsapp">💬</a>

<!-- SCRIPT SLIDER -->
<script>
    let slides = document.querySelectorAll(".slide");
    let index = 0;

    setInterval(() => {
        slides[index].classList.remove("active");
        index = (index + 1) % slides.length;
        slides[index].classList.add("active");
    }, 4000);
</script>

</body>
</html>