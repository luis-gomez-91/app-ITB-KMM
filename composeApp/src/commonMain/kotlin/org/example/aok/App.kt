package org.example.aok

import AppTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import org.example.aok.core.MyNavigation
import org.example.aok.features.admin.docentes.DocentesViewModel
import org.example.aok.features.admin.inscripciones.InscripcionesViewModel
import org.example.aok.features.common.account.AccountViewModel
import org.example.aok.features.common.docBiblioteca.DocBibliotecaViewModel
import org.example.aok.features.common.home.HomeViewModel
import org.example.aok.features.common.login.LoginViewModel
import org.example.aok.features.common.reportes.ReportesViewModel
import org.example.aok.features.student.alu_consulta_general.AluConsultaGeneralViewModel
import org.example.aok.features.student.alu_cronograma.AluCronogramaViewModel
import org.example.aok.features.student.alu_documentos.AluDocumentosViewModel
import org.example.aok.features.student.alu_finanzas.AluFinanzasViewModel
import org.example.aok.features.student.alu_horario.AluHorarioViewModel
import org.example.aok.features.student.alu_malla.AluMallaViewModel
import org.example.aok.features.student.alu_materias.AluMateriasViewModel
import org.example.aok.features.student.alu_matricula.AluMatriculaViewModel
import org.example.aok.features.student.alu_notas.AluNotasViewModel
import org.example.aok.features.student.alu_solicitud_beca.AluSolicitudBecaViewModel
import org.example.aok.features.student.alu_solicitudes_online.AluSolicitudesViewModel
import org.example.aok.features.student.facturacion_electronica.AluFacturacionViewModel
import org.example.aok.features.student.pago_online.PagoOnlineViewModel
import org.example.aok.features.teacher.pro_clases.ProClasesViewModel
import org.example.aok.features.teacher.pro_evaluaciones.ProEvaluacionesViewModel
import org.example.aok.features.teacher.pro_horarios.ProHorariosViewModel

@Composable
fun App(
    homeViewModel : HomeViewModel,
    loginViewModel: LoginViewModel
) {
    val accountViewModel = remember { AccountViewModel() }
    val inscripcionesViewModel = remember { InscripcionesViewModel() }
    val aluFinanzasViewModel = remember { AluFinanzasViewModel() }
    val aluCronogramaViewModel = remember { AluCronogramaViewModel() }
    val aluMallaViewModel = remember { AluMallaViewModel() }
    val aluHorarioViewModel = remember { AluHorarioViewModel() }
    val pagoOnlineViewModel = remember { PagoOnlineViewModel() }
    val aluMateriasViewModel = remember { AluMateriasViewModel() }
    val aluFacturacionViewModel = remember { AluFacturacionViewModel() }
    val aluNotasViewModel = remember { AluNotasViewModel() }
    val aluSolicitudesViewModel = remember { AluSolicitudesViewModel() }
    val docentesViewModel = remember { DocentesViewModel() }
    val proClasesViewModel = remember { ProClasesViewModel() }
    val proHorariosViewModel = remember { ProHorariosViewModel() }
    val aluDocumentosViewModel = remember { AluDocumentosViewModel() }
    val docBibliotecaViewModel = remember { DocBibliotecaViewModel() }
    val aluSolicitudBecaViewModel = remember { AluSolicitudBecaViewModel() }
    val aluConsultaGeneralViewModel = remember { AluConsultaGeneralViewModel() }
    val aluMatriculaViewModel = remember { AluMatriculaViewModel() }
    val reportesViewModel = remember { ReportesViewModel() }
    val proEvaluacionesViewModel = remember { ProEvaluacionesViewModel() }

    AppTheme(
        homeViewModel = homeViewModel
    ) {
        if (true) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                MyNavigation(
                    loginViewModel = loginViewModel,
                    homeViewModel = homeViewModel,
                    inscripcionesViewModel = inscripcionesViewModel,
                    accountViewModel = accountViewModel,
                    aluFinanzasViewModel = aluFinanzasViewModel,
                    aluCronogramaViewModel = aluCronogramaViewModel,
                    aluMallaViewModel = aluMallaViewModel,
                    aluHorarioViewModel = aluHorarioViewModel,
                    pagoOnlineViewModel = pagoOnlineViewModel,
                    aluMateriasViewModel = aluMateriasViewModel,
                    aluFacturacionViewModel = aluFacturacionViewModel,
                    aluNotasViewModel = aluNotasViewModel,
                    docentesViewModel = docentesViewModel,
                    proClasesViewModel = proClasesViewModel,
                    proHorariosViewModel = proHorariosViewModel,
                    aluSolicitudesViewModel = aluSolicitudesViewModel,
                    aluDocumentosViewModel = aluDocumentosViewModel,
                    docBibliotecaViewModel = docBibliotecaViewModel,
                    aluSolicitudBecaViewModel = aluSolicitudBecaViewModel,
                    aluConsultaGeneralViewModel = aluConsultaGeneralViewModel,
                    aluMatriculaViewModel = aluMatriculaViewModel,
                    reportesViewModel = reportesViewModel,
                    proEvaluacionesViewModel = proEvaluacionesViewModel
                )
            }
        } else {
            Box(
                modifier = Modifier.padding(32.dp)
            ) {
                val users by homeViewModel.aokDatabase.userDao().getAllUsers().collectAsState(emptyList())

//                val usuario = User(
//                    username = "prueba",
//                    password = "123"
//                )
//
//                val scope = rememberCoroutineScope()
//
//                scope.launch {
//                    homeViewModel.aokDatabase.userDao().upsert(usuario)
//                }

                LazyColumn {
                    items(users) { user ->
                        Text(user.username)
                        Text(user.password)
                        Spacer(Modifier.height(8.dp))
                    }
                }
            }

        }

    }
}