/*CREAZIONE DataBase*/
CREATE DATABASE "DBProgettoTracciaDue";

/*CREAZIONE sequenzaCodiceProgetti*/
CREATE SEQUENCE sequenzaCodiceProgetti
INCREMENT 1 
START 1000
MINVALUE 1000 
MAXVALUE 99999;

/*CREAZIONE sequenzacodicemeetingfisico*/
CREATE SEQUENCE sequenzaCodiceMeetingFisico 
INCREMENT 1 
START 1000 
MINVALUE 1000 
MAXVALUE 99999;

/*CREAZIONE sequenzacodicemeetingtelematico*/
CREATE SEQUENCE sequenzaCodiceMeetingTelematico 
INCREMENT 1 
START 1000 
MINVALUE 1000 
MAXVALUE 99999;

/*CREAZIONE sequenzacodiceambito*/
CREATE SEQUENCE sequenzaCodiceAmbito 
INCREMENT 1 
START 1000 
MINVALUE 1000 
MAXVALUE 99999;

/*CREAZIONE sequenzacodiceskills*/
CREATE SEQUENCE sequenzaCodiceSkills 
INCREMENT 1 
START 1000 
MINVALUE 1000 
MAXVALUE 99999;

/*CREAZIONE TABELLA SVILUPPATORE*/
CREATE TABLE sviluppatore 
(nome VARCHAR(100) not NULL,
 cognome VARCHAR(100) not NULL, 
 codFiscale VARCHAR(16) CHECK (codFiscale  ~* '^[A-Z][A-Z][A-Z][A-Z][A-Z][A-Z][0-9][0-9][A-Z][0-9][0-9][A-Z][0-9][0-9][0-9][A-Z]'), 
 salarioMedio INTEGER not NULL,
 valutazione VARCHAR CHECK (valutazione LIKE 'Buona' OR  valutazione LIKE 'Mediocre' OR valutazione LIKE 'Male' OR  valutazione LIKE 'NULL' ),
 PRIMARY KEY (codFiscale));
 
/*CREAZIONE TABELLA PROJECT MANAGER*/
CREATE TABLE projectManager 
(nome VARCHAR(100) not NULL, 
 cognome VARCHAR(100) not NULL, 
 codFiscale VARCHAR(16) CHECK (codFiscale  ~* '^[A-Z][A-Z][A-Z][A-Z][A-Z][A-Z][0-9][0-9][A-Z][0-9][0-9][A-Z][0-9][0-9][0-9][A-Z]'), 
 salarioMedio INTEGER not NULL,
 PRIMARY KEY (codFiscale));
 
/*CREAZIONE TABELLA PROGETTO*/
CREATE TABLE progetto 
(nome VARCHAR(255) not NULL UNIQUE, 
 tipologia VARCHAR(255) CHECK (tipologia LIKE 'Ricerca di base' OR tipologia like 'Ricerca industriale' OR tipologia LIKE 'Ricerca sperimentale' OR tipologia like 'Sviluppo sperimentale'),
 codProgetto VARCHAR(255) PRIMARY KEY,
 stato VARCHAR(255) not NULL CHECK (stato LIKE 'Completo' OR stato LIKE 'Incompleto'),
 codfiscale VARCHAR(16) REFERENCES projectmanager(codfiscale));
 
/*CREAZIONE TABELLA D'ASSOCIAZIONE PARTECIPANTI AI PROGETTI*/
CREATE TABLE partecipazioniProgetto
(codfiscale VARCHAR(16) REFERENCES sviluppatore(codFiscale),
codprogetto VARCHAR(255) REFERENCES progetto(codProgetto));

/*CREAZIONE TABELLA AMBITO*/
CREATE TABLE ambito 
(nome VARCHAR(255) not NULL UNIQUE, 
 codAmbito VARCHAR(255) PRIMARY KEY);
 
/*CREAZIONE TABELLA CLASSE D'ASSOCIAZIONE AMBITO */
CREATE TABLE associazioneAmbito
(codProgetto VARCHAR(255) REFERENCES Progetto(codProgetto),
 codAmbito VARCHAR(255) REFERENCES Ambito(codAmbito));
    				
/*CREAZIONE TABELLA MEETING FISICO */
CREATE TABLE meetingFisico 
(codiceMeeting VARCHAR(255) NOT NULL,
 titolo VARCHAR(255) NOT NULL UNIQUE,
 data DATE NOT NULL,
 oraInizio TIME(0) NOT NULL CHECK ( oraInizio < oraFine ),
 oraFine TIME (0) NOT NULL CHECK ( oraFine > oraInizio ),
 luogo VARCHAR,
 nomeSala VARCHAR,
 PRIMARY KEY (codiceMeeting),
 codProgetto VARCHAR(255) REFERENCES progetto(codProgetto),
 codProjectManager VARCHAR(16) REFERENCES projectmanager(codFiscale));
 
 /*CREAZIONE TABELLA D'ASSOCIAZIONE SVILUPPATORI A MEETING FISICO*/
CREATE TABLE partecipazioniSviluppatoreMeetingFisico
(codfiscale VARCHAR(16) REFERENCES sviluppatore(codFiscale),
 codmeeting VARCHAR(255) REFERENCES meetingFisico(codiceMeeting));
 
/*CREAZIONE TABELLA MEETING TELEMATICO */
CREATE TABLE meetingTelematico
(codiceMeeting VARCHAR(255) NOT NULL,
 titolo VARCHAR(255) NOT NULL UNIQUE,
 data DATE NOT NULL,
 oraInizio TIME(0) NOT NULL CHECK ( oraInizio < oraFine ),
 oraFine TIME (0) NOT NULL CHECK ( oraFine > oraInizio ),
 piattaforma VARCHAR,
 PRIMARY KEY (codiceMeeting),
 codProgetto VARCHAR(255) REFERENCES progetto(codProgetto),
 codProjectManager VARCHAR(16) REFERENCES projectmanager(codFiscale));
 
 /*CREAZIONE TABELLA D'ASSOCIAZIONE SVILUPPATORI A MEETING TELEMATICO */
CREATE TABLE partecipazioniSviluppatoreMeetingTelematico
(codfiscale VARCHAR(16) REFERENCES sviluppatore(codFiscale),
codmeeting VARCHAR(255) REFERENCES meetingTelematico(codiceMeeting));

/*CREAZIONE TABELLA SKILLS */
CREATE TABLE skills 
(nomeSkill VARCHAR(100) not NULL UNIQUE, 
 codSkills VARCHAR(255) PRIMARY KEY,
 UNIQUE(nomeSkill));
    				
/*CREAZIONE TABELLA D'ASSOCIAZONE SKILLS PROJECT MANAGER */
CREATE TABLE associazioneSkillsProjectManager
(codFiscale VARCHAR(16) REFERENCES projectManager(codFiscale),
 codSkills VARCHAR(255) REFERENCES skills(codSkills));
 
/*CREAZIONE TABELLA D'ASSOCIAZONE SKILLS SVILUPPATORE */
CREATE TABLE associazioneSkillsSviluppatore
(codFiscale VARCHAR(16) REFERENCES sviluppatore(codFiscale),
 codSkills VARCHAR(255) REFERENCES skills(codSkills));
 
/* CREAZIONE TRIGGER PER GESTIRE LA PARTECIPAZIONE DI UNO STESSO MEMBRO A UN PROGETTO */
CREATE FUNCTION function_partecipazione_al_progetto() RETURNS TRIGGER AS $Trigger_Partecipazione_Al_Progetto$
BEGIN
IF((SELECT P.codfiscale
	FROM progetto AS P
	WHERE (NEW.codfiscale = P.codfiscale AND NEW.codprogetto = P.codprogetto))
   IS NOT NULL) THEN
   DELETE FROM partecipazioniprogetto AS PP
   WHERE PP.codfiscale = NEW.codfiscale;
END IF; 
Return NEW; 
END;
$Trigger_Partecipazione_Al_Progetto$ LANGUAGE plpgsql;
CREATE TRIGGER Trigger_Partecipazione_Al_Progetto
AFTER INSERT OR UPDATE 
ON partecipazioniprogetto 
FOR EACH ROW 
EXECUTE PROCEDURE function_partecipazione_al_progetto();		    		
    				    				  				  
/* CREAZIONE TRIGGER PER EVITARE CHE VENGANO CREATI DUE SKILL CON LO STESSO NOME*/
CREATE FUNCTION function_duplicati_delle_skills() RETURNS TRIGGER AS $Trigger_Duplicati_delle_Skills$
BEGIN
IF((SELECT Sk.nomeskill
	FROM skills AS Sk
	WHERE (NEW.nomeskill = Sk.nomeskill AND Sk.codskills != New.codskills))
   IS NOT NULL) THEN
   DELETE FROM skills AS Sk
   WHERE (NEW.nomeskill = Sk.nomeskill AND Sk.codskills = New.codskills); 
END IF;
Return NEW; 
END;
$Trigger_Duplicati_delle_Skills$ LANGUAGE plpgsql;
CREATE TRIGGER Trigger_Duplicati_delle_Skills 
AFTER INSERT OR UPDATE
ON skills
FOR EACH ROW 
EXECUTE PROCEDURE function_duplicati_delle_skills();


/* CREAZIONE TRIGGER PER  GESTIRE LA CREAZIONE DI UNA SKILL CON CODICE DI UNA SKILL GIA' PRESENTE*/
CREATE FUNCTION function_cod_skills() RETURNS TRIGGER AS $trigger_cod_skills$
BEGIN
WHILE((SELECT DISTINCT Sk.nomeskill
	   FROM skills AS Sk
	   WHERE (Sk.codskills = NEW.codskills))
	  IS NOT NULL)
	  LOOP 
	  NEW.codskills := nextval('sequenzacodiceskills'); 
	END LOOP; 
	RETURN NEW;
END
$trigger_cod_skills$ LANGUAGE plpgsql;
CREATE TRIGGER trigger_cod_skills 
BEFORE INSERT OR UPDATE
ON skills
FOR EACH ROW
EXECUTE PROCEDURE function_cod_skills();

    				
/*   TRIGGER CHE GESTISCE LA CREAZIONE DI UN AMBITO CON CODICE DI UN AMBITO GIA' PRESENTE*/
CREATE FUNCTION function_cod_ambito() RETURNS TRIGGER AS $trigger_cod_ambito$
BEGIN
WHILE((SELECT DISTINCT AM.nome 
	   FROM ambito AS AM 
	   WHERE (AM.codambito = NEW.codambito))
	  IS NOT NULL)
	  LOOP 
	  NEW.codambito := nextval('sequenzacodiceambito');
	  END LOOP;
	  RETURN NEW;
END 
$trigger_cod_ambito$ LANGUAGE plpgsql;
CREATE TRIGGER trigger_cod_ambito 
BEFORE INSERT OR UPDATE
ON ambito
FOR EACH ROW 
EXECUTE PROCEDURE function_cod_ambito();
    				
/*  TRIGGER GESTISCE LA CREAZIONE DI UN PROGETTO CON CODICE DI UN PROGETTO GIA' PRESENTE*/
CREATE FUNCTION function_cod_progetto() RETURNS TRIGGER AS $trigger_cod_progetto$
BEGIN
WHILE((SELECT DISTINCT Pr.nome 
	   FROM progetto AS Pr 
	   WHERE (Pr.codprogetto = NEW.codprogetto)) 
	  IS NOT NULL)
	  LOOP 
	  NEW.codprogetto := nextval('sequenzacodiceprogetti');
	  END LOOP;
	  RETURN NEW; 
END 
$trigger_cod_progetto$ LANGUAGE plpgsql;
CREATE TRIGGER trigger_cod_progetto
BEFORE INSERT  
ON progetto 
FOR EACH ROW 
EXECUTE PROCEDURE function_cod_progetto();
          				
/* CREAZIONE TRIGGER PER GESTIRE LA CREAZIONE DI MEETING FISICI CON CODICE UGUALE A MEETING FISICI 
GIA' PRESENTI E GESTISCE IL CASO IN CUI SI CREI UN MEETING CON NOME UGUALE A UN MEETING GIA' PRESENTE */
CREATE FUNCTION function_cod_meeting_fisico() RETURNS TRIGGER AS $trigger_cod_meeting_fisico$
BEGIN 
WHILE((SELECT DISTINCT Me_F.titolo 
	   FROM meetingfisico AS Me_F 
	   WHERE (Me_F.codicemeeting = NEW.codicemeeting))
	  IS NOT NULL)
	  	LOOP 
	  	NEW.codicemeeting := nextval('sequenzacodicemeetingfisico');
	  	END LOOP; 
IF ((SELECT DISTINCT Me_F.titolo
		FROM meetingfisico AS Me_F
		WHERE (Me_F.titolo = NEW.titolo))
		IS NOT NULL) 
THEN 
NEW.titolo := NEW.titolo ||'.' ||SUBSTRING(NEW.codicemeeting,1,4);
END IF;
RETURN NEW;
END
$trigger_cod_meeting_fisico$ LANGUAGE plpgsql;
CREATE TRIGGER trigger_cod_meeting_fisico
BEFORE INSERT
ON meetingfisico
FOR EACH ROW
EXECUTE PROCEDURE function_cod_meeting_fisico();
 
/* CREAZIONE TRIGGER PER GESTIRE LA CREAZIONE DI MEETING TELEMATICI CON CODICE UGUALE A MEETING TELEMATICI 
GIA' PRESENTI E GESTISCE IL CASO IN CUI SI CREI UN MEETING CON NOME UGUALE A UN MEETING GIA' PRESENTE*/
CREATE FUNCTION function_cod_meeting_telematico() RETURNS TRIGGER AS $trigger_cod_meeting_telematico$
BEGIN
WHILE((SELECT DISTINCT Me_T.titolo 
	   FROM meetingtelematico AS Me_T
	   WHERE (Me_T.codicemeeting = NEW.codicemeeting)) 
	  IS NOT NULL)
	  LOOP 
	  NEW.codicemeeting := nextval('sequenzacodicemeetingtelematico');
	  END LOOP;
	  IF ((SELECT DISTINCT Me_T.titolo
		   FROM meetingtelematico AS Me_T 
		   WHERE (Me_T.titolo = NEW.titolo))
		  IS NOT NULL) THEN
		  NEW.titolo := NEW.titolo ||'.' ||SUBSTRING(NEW.codicemeeting,1,4);
	END IF;
	RETURN NEW;
END
$trigger_cod_meeting_telematico$ LANGUAGE plpgsql;
CREATE TRIGGER trigger_cod_meeting_telematico
BEFORE INSERT
ON meetingtelematico
FOR EACH ROW 
EXECUTE PROCEDURE function_cod_meeting_telematico();
       				