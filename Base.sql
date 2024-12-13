-- On supprime la table si elle existe 
DROP TABLE IF EXISTS HOPE;

-- Création de la table HOPE
CREATE TABLE HOPE (
                      id INT AUTO_INCREMENT PRIMARY KEY, -- Identifiant unique pour chaque entrée
                      Titre VARCHAR(255) NOT NULL,       -- Colonne obligatoire pour le titre
                      Domaine VARCHAR(255) NOT NULL,     -- Colonne obligatoire pour le domaine
                      Lien TEXT NOT NULL,                -- Colonne obligatoire pour le lien
                      Description_simple TEXT,           -- Colonne optionnelle pour la description simple
                      Description_detaillee TEXT,        -- Colonne optionnelle pour la description détaillée
                      Acces TEXT,                        -- Colonne optionnelle pour les informations d'accès
                      Feedback_utilisateurs TEXT         -- Colonne optionnelle pour le feedback des utilisateurs
);

INSERT INTO HOPE (Titre, Domaine, Lien) VALUES ('GitHub Global Campus (GitHub Education)', 'Bouquet de services', 'https://education.github.com/discount_requests/application');
INSERT INTO HOPE (Titre, Domaine, Lien) VALUES ('Coding Rooms', 'Codage / Développement', 'https://www.codingrooms.com/');
INSERT INTO HOPE (Titre, Domaine, Lien) VALUES ('Nowledgeable', 'Codage / Développement', 'https://nowledgeable.com/');
INSERT INTO HOPE (Titre, Domaine, Lien) VALUES ('Jupyter notebook', 'Codage / Développement', 'https://jupyter.org/');
INSERT INTO HOPE (Titre, Domaine, Lien) VALUES ('repl.it', 'Codage / Développement', 'https://replit.com/');
INSERT INTO HOPE (Titre, Domaine, Lien) VALUES ('Google Colaboratory (Colab)', 'Codage / Développement', 'https://colab.research.google.com/?hl=fr');
INSERT INTO HOPE (Titre, Domaine, Lien) VALUES ('Environnements virtuels', 'Hyperviseurs', '???');
INSERT INTO HOPE (Titre, Domaine, Lien) VALUES ('Environnements virtuels', 'Containers', 'https://www.docker.com/');
INSERT INTO HOPE (Titre, Domaine, Lien) VALUES ('TryHackme', 'Cyber sécurité', 'https://tryhackme.com/');
INSERT INTO HOPE (Titre, Domaine, Lien) VALUES ('Hack The Box', 'Cyber sécurité', 'https://www.hackthebox.com/');
INSERT INTO HOPE (Titre, Domaine, Lien) VALUES ('AWS', 'Cloud provider', 'https://signin.aws.amazon.com/signup?request_type=register');
INSERT INTO HOPE (Titre, Domaine, Lien) VALUES ('AWS Academy', 'Formations en ligne', 'https://aws.amazon.com/fr/training/awsacademy/');
INSERT INTO HOPE (Titre, Domaine, Lien) VALUES ('Azure', 'Cloud provider', 'https://azure.microsoft.com/en-us');
INSERT INTO HOPE (Titre, Domaine, Lien) VALUES ('Integral Calculator', 'Mathématiques', 'https://www.integral-calculator.com/');
INSERT INTO HOPE (Titre, Domaine, Lien) VALUES ('eMathHelp', 'Mathématiques', 'https://www.emathhelp.net/en/');
INSERT INTO HOPE (Titre, Domaine, Lien) VALUES ('MultisimLive', 'Electronique', 'https://www.multisim.com/');
INSERT INTO HOPE (Titre, Domaine, Lien) VALUES ('MATLAB & Simulink (MathWorks)', 'Mathématiques', 'https://fr.mathworks.com/products/matlab/student.html');
INSERT INTO HOPE (Titre, Domaine, Lien) VALUES ('Patchwork3D & AccelVR (Lumiscaphe)', 'Réalité virtuelle / augmentée', 'https://resources.lumiscaphe.com/Software_Suite/2023/en/accel-vr.html <br>https://resources.lumiscaphe.com/Software_Suite/2023/en/patchwork-3d.html');
INSERT INTO HOPE (Titre, Domaine, Lien) VALUES ('Kaggle', 'Data Science', 'https://www.kaggle.com/');
INSERT INTO HOPE (Titre, Domaine, Lien) VALUES ('Notion', 'Gestion de projets et collaboration', 'https://www.notion.so/fr-fr');
INSERT INTO HOPE (Titre, Domaine, Lien) VALUES ('Trello', 'Gestion de projets et collaboration', 'https://trello.com/home');
INSERT INTO HOPE (Titre, Domaine, Lien) VALUES ('Code Ocean', 'Data Science', 'https://codeocean.com/');
INSERT INTO HOPE (Titre, Domaine, Lien) VALUES ('Marp', 'Génération de documents', 'https://marp.app/');
INSERT INTO HOPE (Titre, Domaine, Lien) VALUES ('LaTeX', 'Génération de documents', 'https://www.latex-project.org/');
INSERT INTO HOPE (Titre, Domaine, Lien) VALUES ('Material for mkdocs', 'Génération de documents', 'https://squidfunk.github.io/mkdocs-material/');
INSERT INTO HOPE (Titre, Domaine, Lien) VALUES ('Looping', 'Bases de données', 'https://www.looping-mcd.fr/');
INSERT INTO HOPE (Titre, Domaine, Lien) VALUES ('MongoDB Compass', 'Bases de données', 'https://www.mongodb.com/products/tools/compass');
INSERT INTO HOPE (Titre, Domaine, Lien) VALUES ('Wireshark', 'Réseaux', 'https://www.wireshark.org/');

-- Création de la table User
CREATE TABLE User (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(255) NOT NULL,
                      firstName VARCHAR(255) NOT NULL,
                      lastName VARCHAR(255) NOT NULL,
                      role VARCHAR(50) NOT NULL,
                      password VARCHAR(255) NOT NULL
);

-- Insertion d'exemples d'utilisateurs
INSERT INTO User (username, firstName, lastName, role, password)
VALUES
    ('admin_user', 'John', 'Doe', 'Administrateur', 'password123'),
    ('teacher_user', 'Alice', 'Smith', 'Enseignant', 'teacherpass'),
    ('student_user', 'Bob', 'Brown', 'Etudiant', 'studentpass');