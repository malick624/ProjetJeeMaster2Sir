# Architecture Frontend Angular - Projet Entretien Virtuel

## Structure du Projet

```
frontend/
├── src/
│   └── app/
│       ├── core/                    # Cœur de l'application
│       │   ├── services/            # Services globaux
│       │   │   └── ai.service.ts    # Service IA (Gemini)
│       │   ├── guards/              # Guards de routage
│       │   └── interceptors/        # Interceptors HTTP
│       │
│       ├── features/                # Fonctionnalités métier
│       │   ├── auth/                # Authentification
│       │   │   ├── login/
│       │   │   └── register/
│       │   ├── dashboard/           # Dashboard principal
│       │   ├── interview/           # Gestion des entretiens
│       │   │   ├── chat-mode/       # Mode chat
│       │   │   ├── voice-mode/      # Mode voix
│       │   │   └── avatar-mode/     # Mode avatar
│       │   ├── report/              # Rapports d'entretien
│       │   ├── fraud-detection/     # Détection de fraude
│       │   └── profile/             # Profil utilisateur
│       │
│       ├── shared/                  # Éléments partagés
│       │   ├── components/          # Composants réutilisables
│       │   ├── models/              # Modèles de données
│       │   │   └── interview.model.ts
│       │   └── utils/               # Utilitaires
│       │
│       ├── prompts/                 # Prompts IA
│       │   ├── question.prompt.ts
│       │   ├── evaluation.prompt.ts
│       │   └── rapport.prompt.ts
│       │
│       └── layouts/                 # Layouts de l'application
│
└── README_ARCHITECTURE.md
```

## Design System

### Couleurs
```css
--primary: #2563EB;
--secondary: #0F172A;
--accent: #14B8A6;
--success: #22C55E;
--warning: #F59E0B;
--danger: #EF4444;
--background: #F8FAFC;
```

### Typographie
- Font: Poppins ou Inter

### Composants
- **Boutons**: background: #2563EB, color: white
- **Cartes**: border-radius: 16px, box-shadow léger

## Responsabilités par Membre

### Membre 1: Interface Utilisateur
- `features/auth/`
- `features/dashboard/`
- `features/profile/`
- `layouts/`

### Membre 2: Intelligence Artificielle (TOI)
- `core/services/ai.service.ts`
- `prompts/`
- Intégration Google Gemini
- Génération de questions
- Évaluation des réponses
- Génération de rapports

### Membre 3: Avatar + Voix
- `features/interview/avatar-mode/`
- `features/interview/voice-mode/`
- Intégration Ready Player Me
- Intégration Three.js
- Intégration ElevenLabs

### Membre 4: Anti-Cheat
- `features/fraud-detection/`
- Détection changement d'onglet
- Détection copier/coller
- Détection webcam
- Intégration MediaPipe

## Backend API

### Endpoints Principaux
- `POST /api/entretiens/creer` - Créer un entretien
- `POST /api/entretiens/demarrer` - Démarrer un entretien
- `GET /api/entretiens/{id}` - Obtenir un entretien
- `POST /api/entretiens/terminer` - Terminer un entretien
- `POST /api/entretiens/repondre` - Soumettre une réponse

### Services Backend
- `ServiceApplicationEntretien` - Gestion des entretiens
- `ServiceOrchestrateurEntretien` - Orchestration
- `ServiceAnalyseReponses` - Analyse des réponses
- `ServiceModeAvatar` - Mode avatar
- `ServiceModeVoix` - Mode voix
- `ServiceModeChat` - Mode chat

## Installation

```bash
npm install
```

## Développement

```bash
ng serve
```

## Build

```bash
ng build
```

## Git Branches

- `main` - Production
- `develop` - Développement
- `feature/ai` - Fonctionnalités IA (TA BRANCHE)
- `feature/frontend-ui` - Interface utilisateur
- `feature/avatar` - Avatar et voix
- `feature/fraud` - Détection de fraude

## Instructions pour le Membre IA

1. Travailler sur la branche `feature/ai`
2. Implémenter `ai.service.ts` avec Google Gemini
3. Créer et affiner les prompts dans `prompts/`
4. Tester l'intégration avec le backend
5. Faire une Pull Request vers `develop`
