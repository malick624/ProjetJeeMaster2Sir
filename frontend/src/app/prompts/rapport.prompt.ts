/**
 * Prompt pour la génération de rapport d'entretien
 * Utilisé avec Google Gemini pour générer un rapport complet
 */

export const REPORT_GENERATION_PROMPT = `
Tu es un expert en recrutement et en analyse de profils candidats.
Ta tâche est de générer un rapport d'entretien complet basé sur les données suivantes:

INFORMATIONS DU CANDIDAT:
- Nom: {candidateName}
- Poste visé: {targetPosition}
- Entreprise: {company}

DÉTAILS DE L'ENTRETIEN:
- Date: {interviewDate}
- Durée: {duration} minutes
- Mode: {interviewMode}
- Questions posées: {questionCount}
- Réponses évaluées: {answerCount}

RÉSULTATS PAR QUESTION:
{questionResults}

ÉVALUATION GLOBALE:
- Score moyen: {averageScore}
- Compétences démontrées: {demonstratedSkills}
- Points forts: {strengths}
- Points à améliorer: {improvements}

Génère un rapport professionnel qui inclut:
1. Résumé exécutif
2. Analyse détaillée des compétences
3. Évaluation par catégorie (technique, comportementale, culturelle)
4. Recommandation finale (EMBAUCHE / À CONSIDÉRER / REFUS)
5. Plan de développement suggéré

Format de réponse attendu (JSON):
{
  "report": {
    "executiveSummary": "résumé en 3-4 phrases",
    "detailedAnalysis": {
      "technicalSkills": {
        "score": number,
        "comments": "commentaires"
      },
      "behavioralSkills": {
        "score": number,
        "comments": "commentaires"
      },
      "culturalFit": {
        "score": number,
        "comments": "commentaires"
      }
    },
    "recommendation": "HIRE | CONSIDER | REJECT",
    "recommendationJustification": "justification détaillée",
    "developmentPlan": [
      "action 1",
      "action 2"
    ],
    "overallScore": number
  }
}

Le rapport doit être:
- Professionnel et objectif
- Basé sur les données fournies
- Constructif et actionnable
- Adapté au contexte de l'entreprise
`;

export const buildReportPrompt = (
  candidateName: string,
  targetPosition: string,
  company: string,
  interviewDate: string,
  duration: number,
  interviewMode: string,
  questionCount: number,
  answerCount: number,
  questionResults: string,
  averageScore: number,
  demonstratedSkills: string[],
  strengths: string[],
  improvements: string[]
): string => {
  return REPORT_GENERATION_PROMPT
    .replace('{candidateName}', candidateName)
    .replace('{targetPosition}', targetPosition)
    .replace('{company}', company)
    .replace('{interviewDate}', interviewDate)
    .replace('{duration}', duration.toString())
    .replace('{interviewMode}', interviewMode)
    .replace('{questionCount}', questionCount.toString())
    .replace('{answerCount}', answerCount.toString())
    .replace('{questionResults}', questionResults)
    .replace('{averageScore}', averageScore.toString())
    .replace('{demonstratedSkills}', demonstratedSkills.join(', '))
    .replace('{strengths}', strengths.join(', '))
    .replace('{improvements}', improvements.join(', '));
};
