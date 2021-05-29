package org.jesuitas.TFG;

import org.jesuitas.TFG.modelo.actividad.Actividad;
import org.jesuitas.TFG.modelo.administrador.Administrador;
import org.jesuitas.TFG.modelo.alumno.Alumno;
import org.jesuitas.TFG.modelo.etapaEducativa.EtapaEducativa;
import org.jesuitas.TFG.modelo.favorito.Favorito;
import org.jesuitas.TFG.modelo.profesor.Profesor;
import org.jesuitas.TFG.modelo.usuario.User;
import org.jesuitas.TFG.modelo.usuario.UserRole;
import org.jesuitas.TFG.persistencia.repositories.*;
import org.jesuitas.TFG.persistencia.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableSwagger2
@EnableJpaRepositories
public class TfgApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TfgApplication.class, args);
	}

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProfesorRepository profesorRepository;

	@Autowired
	AlumnoRepository alumnoRepository;

	@Autowired
	EtapaEducativaRepository etapaEducativaRepository;

	@Autowired
	ActividadRepository actividadRepository;

	@Autowired
	FavoritoRepository favoritoRepository;

	@Autowired
	AdministradorRepository administradorRepository;

	@Autowired
	UserService userService;

	@Autowired
	@Lazy
	PasswordEncoder passwordEncoder;


	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	Set<UserRole> roles = new HashSet<>();

	@Override
	public void run(String... args) throws Exception {
		///Ejemplo Para el login; en Angular estoy usando el id en el campo nombreUsuario para loguearme
		roles.add(UserRole.ALUMNO);
		userRepository.saveAndFlush(new User("Admin","Carlos Garcia Lazaro","contrasena",roles));
		roles.clear();


		roles.add(UserRole.ALUMNO);
		userService.nuevoUsuario(new User("afdmemdk2234",
				"Carlos",
				"usuariotfg",
				roles));
		roles.clear();

		roles.add(UserRole.PROFESOR);
		userService.nuevoUsuario(new User("165151ddq5551",
				"Mjibanez",
				"profe2021",
				roles));
		roles.clear();


		roles.add(UserRole.ADMIN);
		userService.nuevoUsuario(new User("agByNvRu6UIBvhkjnreR",
				"Admin",
				"colegio",
				roles));
		roles.clear();

		administradorRepository.saveAndFlush(new Administrador("agByNvRu6UIBvhkjnreR","Administrador"));

		profesorRepository.saveAndFlush(new Profesor("165151ddq5551","Maria Jose Ibanez", "619999999"));

		alumnoRepository.saveAndFlush(new Alumno("afdmemdk2234","1384315133F","Carlos Garcia Lazaro", new Date(2021,4,27),"551351843","Avda.Ciudad 1"));


		etapaEducativaRepository.saveAndFlush(new EtapaEducativa("1351sa3d85as","2 GS DAM"));
		etapaEducativaRepository.saveAndFlush(new EtapaEducativa("1511sadasd","1 GM SMR"));
		etapaEducativaRepository.saveAndFlush(new EtapaEducativa("1351sa3das","1 GS ASIR"));


		actividadRepository.saveAndFlush(new Actividad("1122543aaF","Partido Futbol",sdf.parse("02-03-2021"),
				sdf.parse("02-03-2021"),"Partido de fútbol en el que participarán los alumnos de ultimo curso de GS DAM y los profesores del centro.","../../assets/futbol.jpg","1351sa3d85as"));
		actividadRepository.saveAndFlush(new Actividad("1515184bbC","Iglesia",sdf.parse("12-04-2021"),
				sdf.parse("12-04-2021"),"Actividad de oración en la iglesia del centro.","../../assets/iglesia.jpg","1351sa3d85as"));

		actividadRepository.saveAndFlush(new Actividad("1876413fgD",
				"Dia de la Paz",
				sdf.parse("30-04-2021"),
				sdf.parse("1-05-2021"),
				"En este día se celebra el aniversario de la muerte del Mahatma Gandhi (India, 1869-1948), líder pacifista que defendió y promovió la no violencia.",
				"../../assets/diapaz.jpg",
				"1351sa3das"));

		actividadRepository.saveAndFlush(new Actividad("1815100HgZ","Fiestas Colegio",sdf.parse("25-05-2021"),
				sdf.parse("27-05-2021"),"Celebración de las fiestas del centro, en las cuales se incluyen los eventos: Chocolatada,Bocadillo Solidario,música... ","../../assets/fiestasjesuitas.jpg","1351sa3das"));

		actividadRepository.saveAndFlush(new Actividad("1514635vst","Dia de La Rioja",
				sdf.parse("10-06-2021"),
				sdf.parse("10-06-2021"),
				"El Día de La Rioja es la jornada festiva de la comunidad autónoma española de La Rioja. Se celebra el día 9 de junio.",
				"../../assets/rioja.jpg",
				"1351sa3d85as"));

		actividadRepository.saveAndFlush(new Actividad("18437778ss","Semana Ignaciana",sdf.parse("23-06-2021"),
				sdf.parse("29-06-2021"),"Esta es la descripcion de la semana Ignaciana","../../assets/semanaignaciana.jpg","1511sadasd"));


		actividadRepository.saveAndFlush(new Actividad("18437778ss","Celebración Infantil",sdf.parse("26-05-2021"),
					sdf.parse("27-05-2021"),"Actividad para los alumnos de Infantil en la que se desempeñaran diversas actividades para ayudar a la socializacion de los mismos.","../../assets/celebracion3.jpg","1351sa3das"));

		favoritoRepository.saveAndFlush(new Favorito("161dwq419","afdmemdk2234","1876413fgD"));
		favoritoRepository.saveAndFlush(new Favorito("61561qwew","afdmemdk2234","1122543aaF"));
		favoritoRepository.saveAndFlush(new Favorito("16598498q","afdmemdk2234","1514635vst"));
	}
}
