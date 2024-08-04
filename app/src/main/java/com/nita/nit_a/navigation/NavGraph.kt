package com.nita.nit_a.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nita.nit_a.adminScreens.AdminDashboard
import com.nita.nit_a.adminScreens.BannerUpdate
import com.nita.nit_a.adminScreens.FacultyDetailScreen
import com.nita.nit_a.adminScreens.FacultyUpdate
import com.nita.nit_a.adminScreens.MessDaysScreen
import com.nita.nit_a.adminScreens.MessItemScreen
import com.nita.nit_a.adminScreens.NoticeUpdate
import com.nita.nit_a.adminScreens.Noticelist
import com.nita.nit_a.adminScreens.UpdateEvents
import com.nita.nit_a.adminScreens.UpdateMessMenu
import com.nita.nit_a.adminScreens.UpdateScholarship
import com.nita.nit_a.AdminPanel_itemview.PdfRenderer
import com.nita.nit_a.adminScreens.AdminLoginScreen
import com.nita.nit_a.adminScreens.CalenderUpdate
import com.nita.nit_a.adminScreens.NotesBranchScreen
import com.nita.nit_a.adminScreens.NotesScreen
import com.nita.nit_a.adminScreens.NotesSemesterScreen
import com.nita.nit_a.adminScreens.Notes_Pyq_PinDocsScreen
import com.nita.nit_a.adminScreens.PinDocsBranchScreen
import com.nita.nit_a.adminScreens.PinDocsScreen
import com.nita.nit_a.adminScreens.PinDocsSemesterScreen
import com.nita.nit_a.adminScreens.PyqBranchScreen
import com.nita.nit_a.adminScreens.PyqScreen
import com.nita.nit_a.adminScreens.PyqSemesterScreen
import com.nita.nit_a.userScreens.MessMenu.DayScreen
import com.nita.nit_a.userScreens.MessMenu.ItemsScreen
import com.nita.nit_a.userScreens.BioTech.BioTech

import com.nita.nit_a.userScreens.Facilities.Chat
import com.nita.nit_a.userScreens.Chemical.Chemical
import com.nita.nit_a.userScreens.Chemistry.Chemistry
import com.nita.nit_a.userScreens.Civil.Civil
import com.nita.nit_a.userScreens.Facilities.Clubs
import com.nita.nit_a.userScreens.CSE.ComputerScience
import com.nita.nit_a.userScreens.EE.Electrical
import com.nita.nit_a.userScreens.ECE.Electronics
import com.nita.nit_a.userScreens.Facilities.Events.Events
import com.nita.nit_a.userScreens.ForgotPassword
import com.nita.nit_a.userScreens.HomeScreen
import com.nita.nit_a.userScreens.EIE.Instrumentation
import com.nita.nit_a.userScreens.LoginScreen
import com.nita.nit_a.userScreens.Mathematics.Mathematics
import com.nita.nit_a.userScreens.ME.Mechanical
import com.nita.nit_a.userScreens.MessMenu.MessMenu
import com.nita.nit_a.adminScreens.UpdateBranch
import com.nita.nit_a.userScreens.Administrators.AdministratorsScreen
import com.nita.nit_a.userScreens.BioTech.Faculty.BioFacultyScreen
import com.nita.nit_a.userScreens.BioTech.Notes.BioNotesScreen
import com.nita.nit_a.userScreens.BioTech.Notes.BioNotesSemesterScreen
import com.nita.nit_a.userScreens.BioTech.PYQ.BioPYQScreen
import com.nita.nit_a.userScreens.BioTech.PYQ.BioPYQSemesterScreen
import com.nita.nit_a.userScreens.BioTech.PinDocs.BioPinDocsScreen
import com.nita.nit_a.userScreens.BioTech.PinDocs.BioPinDocsSemesterScreen
import com.nita.nit_a.userScreens.CSE.Faculty.CseFacultyScreen
import com.nita.nit_a.userScreens.CSE.Notes.CseNotesScreen
import com.nita.nit_a.userScreens.CSE.Notes.SemesterScreen
import com.nita.nit_a.userScreens.CSE.PYQ.CsePyqScreen
import com.nita.nit_a.userScreens.CSE.PYQ.PYQSemesterScreen
import com.nita.nit_a.userScreens.CSE.PinDocs.CsePinDocsScreen
import com.nita.nit_a.userScreens.CSE.PinDocs.PindocsSemesterScreen
import com.nita.nit_a.userScreens.Chemical.Faculty.ChemicalFacultyScreen
import com.nita.nit_a.userScreens.Chemical.Notes.ChemicalNotesScreen
import com.nita.nit_a.userScreens.Chemical.Notes.ChemicalNotesSemesterScreen
import com.nita.nit_a.userScreens.Chemical.PYQ.ChemicalPYQScreen
import com.nita.nit_a.userScreens.Chemical.PYQ.ChemicalPYQSemesterScreen
import com.nita.nit_a.userScreens.Chemical.PinDocs.ChemicalPinDocsScreen
import com.nita.nit_a.userScreens.Chemical.PinDocs.ChemicalPinDocsSemesterScreen
import com.nita.nit_a.userScreens.Chemistry.Faculty.ChemistryFacultyScreen
import com.nita.nit_a.userScreens.Chemistry.Notes.ChemistryNotesScreen
import com.nita.nit_a.userScreens.Chemistry.Notes.ChemistryNotesSemesterScreen
import com.nita.nit_a.userScreens.Chemistry.PYQ.ChemistryPYQScreen
import com.nita.nit_a.userScreens.Chemistry.PYQ.ChemistryPYQSemesterScreen
import com.nita.nit_a.userScreens.Chemistry.PinDocs.ChemistryPinDocsScreen
import com.nita.nit_a.userScreens.Chemistry.PinDocs.ChemistryPinDocsSemesterScreen
import com.nita.nit_a.userScreens.Civil.Faculty.CivilFacultyScreen
import com.nita.nit_a.userScreens.Civil.Notes.CivilNotesScreen
import com.nita.nit_a.userScreens.Civil.Notes.CivilNotesSemesterScreen
import com.nita.nit_a.userScreens.Civil.PYQ.CivilPYQScreen
import com.nita.nit_a.userScreens.Civil.PYQ.CivilPYQSemesterScreen
import com.nita.nit_a.userScreens.Civil.PinDocs.CivilPinDocsScreen
import com.nita.nit_a.userScreens.Civil.PinDocs.CivilPinDocsSemesterScreen
import com.nita.nit_a.userScreens.ContactUs
import com.nita.nit_a.userScreens.ECE.Faculty.EceFacultyScreen
import com.nita.nit_a.userScreens.ECE.Notes.EceNotesScreen
import com.nita.nit_a.userScreens.ECE.Notes.EceSemesterScreen
import com.nita.nit_a.userScreens.ECE.PYQ.EcePYQScreen
import com.nita.nit_a.userScreens.ECE.PYQ.EcePYQSemesterScreen
import com.nita.nit_a.userScreens.ECE.PinDocs.EcePinDocsScreen
import com.nita.nit_a.userScreens.ECE.PinDocs.EcePinDocsSemesterScreen
import com.nita.nit_a.userScreens.EE.Faculty.EeFacultyScreen
import com.nita.nit_a.userScreens.EE.Notes.EeNotesScreen
import com.nita.nit_a.userScreens.EE.Notes.EeNotesSemesterScreen
import com.nita.nit_a.userScreens.EE.PYQ.EePYQScreen
import com.nita.nit_a.userScreens.EE.PYQ.EePYQSemesterScreen
import com.nita.nit_a.userScreens.EE.PinDocs.EePinDocsScreen
import com.nita.nit_a.userScreens.EE.PinDocs.EePinDocsSemesterScreen
import com.nita.nit_a.userScreens.EIE.Faculty.EieFacultyScreen
import com.nita.nit_a.userScreens.EIE.Notes.EieNotesScreen
import com.nita.nit_a.userScreens.EIE.Notes.EieNotesSemesterScreen
import com.nita.nit_a.userScreens.EIE.PYQ.EiePYQScreen
import com.nita.nit_a.userScreens.EIE.PYQ.EiePYQSemesterScreen
import com.nita.nit_a.userScreens.EIE.PinDocs.EiePinDocsScreen
import com.nita.nit_a.userScreens.EIE.PinDocs.EiePinDocsSemesterScreen
import com.nita.nit_a.userScreens.Facilities.Notice.Notice
import com.nita.nit_a.userScreens.Physics.Physics
import com.nita.nit_a.userScreens.Production.Production
import com.nita.nit_a.userScreens.ProfileScreen
import com.nita.nit_a.userScreens.RegistrationScreen
import com.nita.nit_a.userScreens.Facilities.Scholarship.Scholarship
import com.nita.nit_a.userScreens.FirstYear.FirstYear
import com.nita.nit_a.userScreens.FirstYear.Notes.FirstYearNotesScreen
import com.nita.nit_a.userScreens.FirstYear.Notes.FirstYearNotesSemesterScreen
import com.nita.nit_a.userScreens.FirstYear.PYQ.FirstYearPYQScreen
import com.nita.nit_a.userScreens.FirstYear.PinDocs.FirstYearPinDocsScreen
import com.nita.nit_a.userScreens.FirstYear.PinDocs.FirstYearPinDocsSemesterScreen
import com.nita.nit_a.userScreens.ME.Faculty.MechFacultyScreen
import com.nita.nit_a.userScreens.ME.Notes.MechNotesScreen
import com.nita.nit_a.userScreens.ME.Notes.MechNotesSemesterScreen
import com.nita.nit_a.userScreens.ME.PYQ.MechPYQScreen
import com.nita.nit_a.userScreens.ME.PYQ.MechPYQSemesterScreen
import com.nita.nit_a.userScreens.ME.PinDocs.MechPinDocsScreen
import com.nita.nit_a.userScreens.ME.PinDocs.MechPinDocsSemesterScreen
import com.nita.nit_a.userScreens.Mathematics.Faculty.MathFacultyScreen
import com.nita.nit_a.userScreens.Mathematics.Notes.MathNotesScreen
import com.nita.nit_a.userScreens.Mathematics.Notes.MathNotesSemesterScreen
import com.nita.nit_a.userScreens.Mathematics.PYQ.MathPYQScreen
import com.nita.nit_a.userScreens.Mathematics.PYQ.MathPYQSemesterScreen
import com.nita.nit_a.userScreens.Mathematics.PinDocs.MathPinDocsScreen
import com.nita.nit_a.userScreens.Mathematics.PinDocs.MathPinDocsSemesterScreen
import com.nita.nit_a.userScreens.Physics.Faculty.PhyFacultyScreen
import com.nita.nit_a.userScreens.Physics.Notes.PhyNotesScreen
import com.nita.nit_a.userScreens.Physics.Notes.PhyNotesSemesterScreen
import com.nita.nit_a.userScreens.Physics.PYQ.PhyPYQScreen
import com.nita.nit_a.userScreens.Physics.PYQ.PhyPYQSemesterScreen
import com.nita.nit_a.userScreens.Physics.PinDocs.PhyPinDocsScreen
import com.nita.nit_a.userScreens.Physics.PinDocs.PhyPinDocsSemesterScreen
import com.nita.nit_a.userScreens.Production.Faculty.PeFacultyScreen
import com.nita.nit_a.userScreens.Production.Notes.PeNotesScreen
import com.nita.nit_a.userScreens.Production.Notes.PeNotesSemesterScreen
import com.nita.nit_a.userScreens.Production.PYQ.PePYQScreen
import com.nita.nit_a.userScreens.Production.PYQ.PePYQSemesterScreen
import com.nita.nit_a.userScreens.Production.PinDocs.PePinDocsScreen
import com.nita.nit_a.userScreens.Production.PinDocs.PePinDocsSemesterScreen
import com.nita.nit_a.userScreens.Splash

@Composable

fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash" ) {

        composable(route = "splash"){
            Splash(navController)
        }
        composable(route = "login") {
            LoginScreen(navController)
        }
        composable(route = "registration") {
            RegistrationScreen(navController)
        }
        composable(route = "forgot") {
            ForgotPassword(navController)
        }
        composable(route = "home") {
            HomeScreen(navController)
        }
        composable(route = "profile") {
            ProfileScreen(navController)

        }

        composable(route = "admin_login") {
            AdminLoginScreen(navController)

        }

        composable(route = "admin_dashboard") {
            AdminDashboard(navController)
        }
        composable(route = "notice") {
            Notice(navController)
        }
        composable(route = "mess_menu") {
            MessMenu(navController)
        }

        composable(route = "chat") {
            Chat(navController)
        }
        composable(route = "scholarship") {
            Scholarship(navController)
        }
        composable(route = "clubs") {
            Clubs(navController)
        }
        composable(route = "events") {
            Events(navController)
        }
        composable(route = "cse") {
            ComputerScience(navController)
        }
        composable(route = "ece") {
            Electronics(navController)
        }
        composable(route = "ee") {
            Electrical(navController)
        }
        composable(route = "eie") {
            Instrumentation(navController)
        }
        composable(route = "me") {
            Mechanical(navController)
        }
        composable(route = "ce") {
            Civil(navController)
        }
        composable(route = "che") {
            Chemical(navController)
        }
        composable(route = "pe") {
            Production(navController)
        }
        composable(route = "be") {
            BioTech(navController)
        }
        composable(route = "phy") {
            Physics(navController)
        }
        composable(route = "chem") {
            Chemistry(navController)
        }
        composable(route = "math") {
            Mathematics(navController)
        }
        composable(route = "banner_update") {
            BannerUpdate(navController)
        }
        composable(route = "notice_update") {
            NoticeUpdate(navController)
        }
        composable(route = "notice_list") {
            Noticelist(navController)
        }
        composable(
            route = "pdfScreen/{url}",
            arguments = listOf(navArgument("url") { type = NavType.StringType })
        ) { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url")
            PdfRenderer(url = url!!, navController)
        }

        composable(route = "faculty_update") {
            FacultyUpdate(navController)
        }
        composable(
            route = "facultydetail/{catName}",
            arguments = listOf(navArgument("catName") { type = NavType.StringType })
        ) { backStackEntry ->
            val catName = backStackEntry.arguments!!.getString("catName")
            if (catName != null) {
                FacultyDetailScreen(navController, catName)
            }
        }

        composable(route = "scholarship_update") {
            UpdateScholarship(navController)
        }

        composable(route = "events_update") {
            UpdateEvents(navController)
        }

        composable(route = "messmenu_update") {
            UpdateMessMenu(navController)
        }

        composable(route = "department_update") {
            UpdateBranch(navController)
        }



        composable(
            route = "day/{hostel}",
            arguments = listOf(navArgument("hostel") { type = NavType.StringType })
        ) { backStackEntry ->
            val hostel = backStackEntry.arguments!!.getString("hostel")
            if (hostel != null) {
                MessDaysScreen(navController, hostel)
            }
        }

        composable(
            route = "MessItems/{hostel}/{day}",
            arguments = listOf(
                navArgument("hostel") { type = NavType.StringType },
                navArgument("day") { type = NavType.StringType })
        ) { backStackEntry ->
            val hostel = backStackEntry.arguments!!.getString("hostel")
            val day = backStackEntry.arguments!!.getString("day")
            if (hostel != null && day != null) {
                MessItemScreen(navController, hostel, day)
            }


        }

        composable(
            route = "user_day/{hostel}",
            arguments = listOf(navArgument("hostel") { type = NavType.StringType })
        ) { backStackEntry ->
            val hostel = backStackEntry.arguments!!.getString("hostel")
            if (hostel != null) {
                DayScreen(navController, hostel)
            }
        }



        composable(
            route = "MessItem/{hostel}/{day}",
            arguments = listOf(
                navArgument("hostel") { type = NavType.StringType },
                navArgument("day") { type = NavType.StringType })
        ) { backStackEntry ->
            val hostel = backStackEntry.arguments!!.getString("hostel")
            val day = backStackEntry.arguments!!.getString("day")
            if (hostel != null && day != null) {
                 ItemsScreen(hostel , day , navController )
            }


        }

        composable(route = "notes_branch_screen") {
            NotesBranchScreen(navController)
        }

        composable(route ="Notes_Pyq_PinDocs") {
             Notes_Pyq_PinDocsScreen(navController)
        }

        composable(
            route = "notes_semester/{branch}",
            arguments = listOf(navArgument("branch") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            if (branch != null) {
                NotesSemesterScreen(navController, branch)
            }
        }

        composable(
            route =  "notes/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                 NotesScreen(navController, branch , semester)
            }


        }

        composable(route = "pyq_branch_screen") {
            PyqBranchScreen(navController )
        }

        composable(
            route = "pyq_semester/{branch}",
            arguments = listOf(navArgument("branch") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            if (branch != null) {
                PyqSemesterScreen(navController, branch)
            }
        }

        composable(
            route =  "pyq/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                PyqScreen(navController, branch , semester)
            }


        }

        composable(route = "pindocs_branch_screen") {
            PinDocsBranchScreen(navController )
        }

        composable(
            route = "pindocs_semester/{branch}",
            arguments = listOf(navArgument("branch") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            if (branch != null) {
                PinDocsSemesterScreen(navController, branch)
            }
        }

        composable(
            route =  "pindocs/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                PinDocsScreen(navController, branch , semester)
            }


        }

        composable(route =  "notes_screen") {
             SemesterScreen(navController)
        }

        composable(
            route =  "csenotes/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                CseNotesScreen(navController, branch , semester)
            }


        }

        composable(route = "pyqsemesterscreen") {
            PYQSemesterScreen(navController)
        }

        composable(
            route =  "csepyq/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                CsePyqScreen(navController, branch , semester)
            }


        }

        composable(route = "pindocs_semester_screen") {
            PindocsSemesterScreen(navController)
        }

        composable(
            route =  "cse_pindocs/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                CsePinDocsScreen(navController, branch , semester)
            }


        }

        composable(route = "cse_faculty") {
             CseFacultyScreen(navController )
        }

        composable(route = "ece_faculty") {
            EceFacultyScreen(navController )
        }

        composable(route = "ece_semester_screen") {
             EceSemesterScreen(navController)
        }

        composable(
            route =  "ece_notes/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                 EceNotesScreen(navController, branch , semester)
            }


        }

        composable(route = "ece_pindocs_semester") {
            EcePinDocsSemesterScreen(navController)
        }

        composable(
            route =  "ece_pindocs/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                EcePinDocsScreen(navController, branch , semester)
            }


        }

        composable(route = "ece_pyq_semester") {
            EcePYQSemesterScreen(navController)
        }

        composable(
            route =  "ece_pyq/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                EcePYQScreen(navController, branch , semester)
            }


        }

        composable(route = "ee_faculty") {
            EeFacultyScreen(navController )
        }

        composable(route = "ee_notes_semester") {
            EeNotesSemesterScreen(navController)
        }

        composable(
            route =  "ee_notes/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                EeNotesScreen(navController, branch , semester)
            }


        }

        composable(route = "ee_pindocs_semester") {
            EePinDocsSemesterScreen(navController)
        }

        composable(
            route =  "ee_pindocs/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                EePinDocsScreen(navController, branch , semester)
            }


        }


        composable(route = "ee_pyq_semester") {
            EePYQSemesterScreen(navController)
        }

        composable(
            route =  "ee_pyq/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                EePYQScreen(navController, branch , semester)
            }


        }

        composable(route = "eie_faculty") {
            EieFacultyScreen(navController )
        }

        composable(route = "eie_notes_semester") {
            EieNotesSemesterScreen(navController)
        }

        composable(
            route =  "eie_notes/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                EieNotesScreen(navController, branch , semester)
            }


        }

        composable(route = "eie_pindocs_semester") {
            EiePinDocsSemesterScreen(navController)
        }

        composable(
            route =  "eie_pindocs/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                EiePinDocsScreen(navController, branch , semester)
            }


        }

        composable(route = "eie_pyq_semester") {
            EiePYQSemesterScreen(navController)
        }

        composable(
            route =  "eie_pyq/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                EiePYQScreen(navController, branch , semester)
            }


        }

        composable(route = "math_faculty") {
            MathFacultyScreen(navController )
        }

        composable(route = "math_notes_semester") {
            MathNotesSemesterScreen(navController)
        }

        composable(
            route =  "math_notes/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                MathNotesScreen(navController, branch , semester)
            }


        }

        composable(route = "math_pindocs_semester") {
            MathPinDocsSemesterScreen(navController)
        }

        composable(
            route =  "math_pindocs/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                MathPinDocsScreen(navController, branch , semester)
            }


        }

        composable(route = "math_pyq_semester") {
            MathPYQSemesterScreen(navController)
        }

        composable(
            route =  "math_pyq/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                MathPYQScreen(navController, branch , semester)
            }


        }


        composable(route = "mech_faculty") {
            MechFacultyScreen(navController )
        }

        composable(route = "mech_notes_semester") {
            MechNotesSemesterScreen(navController)
        }

        composable(
            route =  "mech_notes/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                MechNotesScreen(navController, branch , semester)
            }


        }

        composable(route = "mech_pindocs_semester") {
            MechPinDocsSemesterScreen(navController)
        }

        composable(
            route =  "mech_pindocs/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                MechPinDocsScreen(navController, branch , semester)
            }


        }

        composable(route = "mech_pyq_semester") {
            MechPYQSemesterScreen(navController)
        }

        composable(
            route =  "mech_pyq/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                MechPYQScreen(navController, branch , semester)
            }


        }


        composable(route = "physics_faculty") {
            PhyFacultyScreen(navController )
        }

        composable(route = "physics_notes_semester") {
            PhyNotesSemesterScreen(navController)
        }

        composable(
            route =  "physics_notes/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                PhyNotesScreen(navController, branch , semester)
            }


        }

        composable(route = "physics_pindocs_semester") {
            PhyPinDocsSemesterScreen(navController)
        }

        composable(
            route =  "physics_pindocs/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                PhyPinDocsScreen(navController, branch , semester)
            }


        }

        composable(route = "physics_pyq_semester") {
            PhyPYQSemesterScreen(navController)
        }

        composable(
            route =  "physics_pyq/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                PhyPYQScreen(navController, branch , semester)
            }


        }

        composable(route = "pe_faculty") {
            PeFacultyScreen(navController )
        }

        composable(route = "pe_notes_semester") {
            PeNotesSemesterScreen(navController)
        }

        composable(
            route =  "pe_notes/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                PeNotesScreen(navController, branch , semester)
            }


        }

        composable(route = "pe_pindocs_semester") {
            PePinDocsSemesterScreen(navController)
        }

        composable(
            route =  "pe_pindocs/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                PePinDocsScreen(navController, branch , semester)
            }


        }

        composable(route = "pe_pyq_semester") {
            PePYQSemesterScreen(navController)
        }

        composable(
            route =  "pe_pyq/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                PePYQScreen(navController, branch , semester)
            }


        }


        composable(route = "bio_faculty") {
            BioFacultyScreen(navController )
        }

        composable(route = "bio_notes_semester") {
            BioNotesSemesterScreen(navController)
        }

        composable(
            route =  "bio_notes/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                BioNotesScreen(navController, branch , semester)
            }


        }

        composable(route = "bio_pindocs_semester") {
            BioPinDocsSemesterScreen(navController)
        }

        composable(
            route =  "bio_pindocs/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                BioPinDocsScreen(navController, branch , semester)
            }


        }

        composable(route = "bio_pyq_semester") {
            BioPYQSemesterScreen(navController)
        }

        composable(
            route =  "bio_pyq/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                BioPYQScreen(navController, branch , semester)
            }


        }


        composable(route = "chemical_faculty") {
            ChemicalFacultyScreen(navController )
        }

        composable(route = "chemical_notes_semester") {
            ChemicalNotesSemesterScreen(navController)
        }

        composable(
            route =  "chemical_notes/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                ChemicalNotesScreen(navController, branch , semester)
            }


        }

        composable(route = "chemical_pindocs_semester") {
            ChemicalPinDocsSemesterScreen(navController)
        }

        composable(
            route =  "chemical_pindocs/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                ChemicalPinDocsScreen(navController, branch , semester)
            }


        }

        composable(route = "chemical_pyq_semester") {
            ChemicalPYQSemesterScreen(navController)
        }

        composable(
            route =  "chemical_pyq/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                ChemicalPYQScreen(navController, branch , semester)
            }


        }


        composable(route = "chemistry_faculty") {
            ChemistryFacultyScreen(navController )
        }

        composable(route = "chemistry_notes_semester") {
            ChemistryNotesSemesterScreen(navController)
        }

        composable(
            route =  "chemistry_notes/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                ChemistryNotesScreen(navController, branch , semester)
            }


        }

        composable(route = "chemistry_pindocs_semester") {
            ChemistryPinDocsSemesterScreen(navController)
        }

        composable(
            route =  "chemistry_pindocs/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                ChemistryPinDocsScreen(navController, branch , semester)
            }


        }

        composable(route = "chemistry_pyq_semester") {
            ChemistryPYQSemesterScreen(navController)
        }

        composable(
            route =  "chemistry_pyq/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                ChemistryPYQScreen(navController, branch , semester)
            }


        }


        composable(route = "civil_faculty") {
            CivilFacultyScreen(navController )
        }

        composable(route = "civil_notes_semester") {
            CivilNotesSemesterScreen(navController)
        }

        composable(
            route =  "civil_notes/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                CivilNotesScreen(navController, branch , semester)
            }


        }

        composable(route = "civil_pindocs_semester") {
            CivilPinDocsSemesterScreen(navController)
        }

        composable(
            route =  "civil_pindocs/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                CivilPinDocsScreen(navController, branch , semester)
            }


        }

        composable(route = "civil_pyq_semester") {
            CivilPYQSemesterScreen(navController)
        }

        composable(
            route =  "civil_pyq/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                CivilPYQScreen(navController, branch , semester)
            }


        }

        composable(route = "calender") {
            CalenderUpdate(navController)
        }

        composable(route = "first_year") {
            FirstYear(navController)
        }

        composable(route = "administrators") {
            AdministratorsScreen(navController)
        }

        composable(route = "contactus") {
            ContactUs(navController)
        }

        composable(route = "1styear_notes_semester") {
            FirstYearNotesSemesterScreen(navController)
        }

        composable(
            route =  "first_year_notes/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                FirstYearNotesScreen(navController, branch , semester)
            }


        }

        composable(route = "1styear_pindocs_semester") {
            FirstYearPinDocsSemesterScreen(navController)
        }

        composable(
            route =  "first_year_pindocs/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                FirstYearPinDocsScreen(navController, branch , semester)
            }


        }

        composable(route = "1styear_pyq_semester") {
            CivilPYQSemesterScreen(navController)
        }

        composable(
            route =  "first_year_pyq/{branch}/{semester}",
            arguments = listOf(
                navArgument("branch") { type = NavType.StringType },
                navArgument("semester") { type = NavType.StringType })
        ) { backStackEntry ->
            val branch = backStackEntry.arguments!!.getString("branch")
            val semester = backStackEntry.arguments!!.getString("semester")
            if (branch != null && semester!= null) {
                FirstYearPYQScreen(navController, branch , semester)
            }


        }


    }
}
