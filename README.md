# Simulateur d'Entretien d'Embauche - API Documentation

## 📋 Description

Ce projet est un simulateur d'entretien d'embauche alimenté par l'intelligence artificielle, construit avec Spring Boot et Spring AI. L'application utilise une architecture hexagonale (Ports & Adapters) pour séparer la logique métier des détails techniques.

L'objectif est de permettre aux candidats de s'entraîner aux entretiens à travers trois modes d'interaction :
- **Mode Chat** : Questions et réponses par écrit
- **Mode Vocal** : Questions audio et réponses vocales
- **Mode Avatar** : Mode vocal avec avatar animé simulant le recruteur

## 🏗️ Architecture Hexagonale

L'architecture est organisée en trois couches principales :

### Domain (Cœur de l'application)
Contient la logique métier pure, indépendante de toute technologie externe.

- **Modèles** : `SessionEntretien`, `Question`, `Reponse`, `DescriptionPoste`, `ProfilCandidat`, `RapportEntretien`, `EtatEntretien`, `ModeInteraction`
- **Ports d'entrée** : Interfaces appelées par les adaptateurs d'entrée (`PortGestionEntretien`, `PortGenerationQuestions`, `PortEvaluationReponse`, `PortGenerationRapport`, `PortInteractionVoix`, `PortInteractionAvatar`)
- **Ports de sortie** : Interfaces implémentées par les adaptateurs de sortie (`EntretienRepository`, `DescriptionPosteRepository`, `PortServiceChatIA`, `PortSyntheseVocale`, `PortSyntheseParole`, `PortGenerationAvatar`, `PortAnalyseDocument`)
- **Services** : Logique métier complexe (`ServiceOrchestrateurEntretien`, `ServiceAdaptationQuestions`, `ServiceAnalyseReponses`, `ServiceGenerateurFeedback`)

### Application (Couche d'orchestration)
Orchestre les interactions entre le domaine et les adaptateurs.

- **DTOs et Commandes** : Objets de transfert de données pour les requêtes/réponses (`DTOResponseEntretien`, `DTOQuestion`, `DTOReponse`, `DTORapport`, `CommandeCreerEntretien`, `CommandeDemarrerEntretien`, `CommandeSoumettreReponse`, `RequeteInitialiserEntretien`)
- **Services d'Application** : Implémentent les ports d'entrée et coordonnent les opérations (`ServiceApplicationEntretien`, `ServiceModeChat`, `ServiceModeVoix`, `ServiceModeAvatar`, `ServiceApplicationRapport`)

### Infrastructure (Adaptateurs techniques)
Contient tous les détails techniques et les implémentations des ports.

- **REST Controllers** : Adaptateurs HTTP (`ControleurEntretien`, `ControleurChat`, `ControleurVoix`, `ControleurAvatar`, `ControleurRapport`)
- **WebSocket Handlers** : Communications en temps réel (`InterviewWebSocketHandler`, `VoiceWebSocketHandler`, `AvatarWebSocketHandler`)
- **AI Adapters** : Intégration des services IA (`GPT4oAdapter`, `WhisperAdapter`, `ElevenLabsAdapter`, `DIDAdapter`, `DocumentAnalysisAdapter`)
- **Persistence** : JPA/Hibernate pour PostgreSQL (`InterviewEntity`, `JobDescriptionEntity`, `JpaInterviewRepository`, `JpaJobDescriptionRepository`, `SpringInterviewRepository`, `SpringJobDescriptionRepository`)
- **Configurations** : Configurations Spring (`JpaConfig`, `SecurityConfig`, `SpringAIConfig`, `WebConfig`, `ConfigurationWebSocket`)

## 🚀 Technologies Utilisées

- **Java 17**
- **Spring Boot 4.0.6**
- **Spring AI** (pour GPT-4o)
- **Spring Data JPA**
- **PostgreSQL**
- **Flyway** (migrations de base de données)
- **WebSocket** (communications en temps réel)
- **WebFlux** (client HTTP réactif)
- **Lombok**
- **SpringDoc OpenAPI** (documentation Swagger)

## 🔧 Configuration Requise

### Variables d'Environnement

Configurez les variables d'environnement suivantes :

```bash
OPENAI_API_KEY=your-openai-api-key
ELEVENLABS_API_KEY=your-elevenlabs-api-key
DID_API_KEY=your-did-api-key
```

### Base de Données

L'application utilise PostgreSQL. Configurez votre base de données :

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/interview_simulator
spring.datasource.username=postgres
spring.datasource.password=postgres
```

## 📡 API REST Endpoints

### Interview Management

#### Créer un entretien
```http
POST /api/interviews
Content-Type: application/json

{
  "candidateId": "uuid",
  "jobDescriptionContent": "Description du poste...",
  "mode": "CHAT",
  "questionCount": 10
}
```

#### Démarrer un entretien
```http
POST /api/interviews/{id}/start
```

#### Récupérer un entretien
```http
GET /api/interviews/{id}
```

#### Terminer un entretien
```http
POST /api/interviews/{id}/complete
```

### Mode Chat

#### Obtenir la prochaine question
```http
GET /api/chat/interviews/{interviewId}/next-question
```

#### Soumettre une réponse
```http
POST /api/chat/interviews/{interviewId}/answers
Content-Type: application/json

{
  "questionId": "uuid",
  "answerContent": "Ma réponse..."
}
```

### Mode Vocal

#### Obtenir la prochaine question
```http
GET /api/voice/interviews/{interviewId}/next-question
```

#### Synthétiser une question en audio
```http
GET /api/voice/synthesize?text=Question+text
```

#### Transcrire une réponse audio
```http
POST /api/voice/transcribe
Content-Type: multipart/form-data

audio: [fichier audio]
```

#### Soumettre une réponse
```http
POST /api/voice/interviews/{interviewId}/answers
Content-Type: application/json

{
  "questionId": "uuid",
  "answerContent": "Réponse transrite..."
}
```

### Mode Avatar

#### Obtenir la prochaine question
```http
GET /api/avatar/interviews/{interviewId}/next-question
```

#### Générer une vidéo d'avatar
```http
GET /api/avatar/generate-video?text=Question+text
```

#### Générer une frame d'avatar
```http
GET /api/avatar/generate-frame?text=Question+text&emotion=neutral
```

### Rapports

#### Générer un rapport d'entretien
```http
GET /api/reports/interviews/{interviewId}
```

## 🔌 WebSocket Endpoints

### Interview WebSocket
```
ws://localhost:8080/ws/interview
```

**Message format :**
```json
{
  "action": "get_interview",
  "interviewId": "uuid"
}
```

### Voice WebSocket
```
ws://localhost:8080/ws/voice
```

**Message format :**
```json
{
  "action": "get_next_question",
  "interviewId": "uuid"
}
```

```json
{
  "action": "synthesize",
  "text": "Question text"
}
```

### Avatar WebSocket
```
ws://localhost:8080/ws/avatar
```

**Message format :**
```json
{
  "action": "get_next_question",
  "interviewId": "uuid"
}
```

```json
{
  "action": "generate_video",
  "text": "Question text"
}
```

```json
{
  "action": "generate_frame",
  "text": "Question text",
  "emotion": "neutral"
}
```

## 🎯 Services IA Intégrés

| Service | Modèle | Utilisation |
|---------|--------|-------------|
| Chat | GPT-4o | Génération de questions, évaluation des réponses, génération de rapports |
| STT | Whisper | Reconnaissance vocale |
| TTS | ElevenLabs | Synthèse vocale |
| Avatar | D-ID / HeyGen | Génération d'avatar animé |

## 📦 Installation et Démarrage

### Prérequis
- Java 17 ou supérieur
- Maven 3.6+
- PostgreSQL 14+
- Clés API pour les services IA

### Étapes d'installation

1. **Cloner le repository**
```bash
git clone <repository-url>
cd entretien
```

2. **Configurer les variables d'environnement**
```bash
export OPENAI_API_KEY=your-key
export ELEVENLABS_API_KEY=your-key
export DID_API_KEY=your-key
```

3. **Démarrer PostgreSQL**
```bash
# Assurez-vous que PostgreSQL est en cours d'exécution
# Créez la base de données 'interview_simulator'
createdb interview_simulator
```

4. **Compiler et démarrer l'application**
```bash
./mvnw clean install
./mvnw spring-boot:run
```

L'application sera accessible sur `http://localhost:8080`

### Documentation Swagger

Une fois l'application démarrée, accédez à la documentation Swagger :
```
http://localhost:8080/swagger-ui.html
```

## 📊 Structure du Projet

```
src/main/java/com/master2sir/entretien/
├── domain/
│   ├── model/              # Entités métier
│   ├── port/
│   │   ├── in/            # Ports d'entrée
│   │   └── out/           # Ports de sortie
│   └── service/           # Services du domaine
├── application/
│   ├── service/           # Services d'application
│   └── usecase/           # DTOs et commandes
└── infrastructure/
    ├── adapter/
    │   ├── input/
    │   │   ├── rest/      # Contrôleurs REST
    │   │   └── websocket/ # Handlers WebSocket
    │   └── output/
    │       ├── ai/        # Adaptateurs IA
    │       └── persistence/ # Adaptateurs JPA
    └── config/           # Configurations Spring
```

## 🔒 Sécurité

Par défaut, l'application est configurée avec :
- CSRF désactivé
- Endpoints API accessibles sans authentification (pour le développement)
- CORS configuré pour autoriser toutes les origines

**Note :** Pour la production, configurez une authentification appropriée (OAuth2, JWT, etc.).

## 🧪 Tests

Pour exécuter les tests :
```bash
./mvnw test
```

## 📝 Exemples d'Utilisation

### Créer un entretien en mode Chat
```bash
curl -X POST http://localhost:8080/api/interviews \
  -H "Content-Type: application/json" \
  -d '{
    "candidateId": "550e8400-e29b-41d4-a716-446655440000",
    "jobDescriptionContent": "Développeur Java Spring avec expérience en microservices",
    "mode": "CHAT",
    "questionCount": 5
  }'
```

### Obtenir la première question
```bash
curl http://localhost:8080/api/chat/interviews/{interviewId}/next-question
```

### Soumettre une réponse
```bash
curl -X POST http://localhost:8080/api/chat/interviews/{interviewId}/answers \
  -H "Content-Type: application/json" \
  -d '{
    "questionId": "question-uuid",
    "answerContent": "Jai 5 ans dexpérience avec Spring Boot..."
  }'
```

### Générer le rapport final
```bash
curl http://localhost:8080/api/reports/interviews/{interviewId}
```
