/**
 * Prompt pour la génération de questions d'entretien
 * Utilisé avec Google Gemini pour générer des questions pertinentes
 */

export const QUESTION_GENERATION_PROMPT = `
Tu es un expert en recrutement et en entretiens d'embauche. 
Ta tâche est de générer des questions d'entretien pertinentes basées sur les informations suivantes:

POSTE: {jobTitle}
ENTREPRISE: {company}
DESCRIPTION DU POSTE: {jobDescription}
NIVEAU: {level}
NOMBRE DE QUESTIONS: {questionCount}

Génère {questionCount} questions d'entretien professionnelles qui couvrent:
1. Les compétences techniques requises
2. Les compétences comportementales (soft skills)
3. L'adéquation culturelle avec l'entreprise
4. La motivation et l'intérêt pour le poste

Format de réponse attendu (JSON):
{
  "questions": [
    {
      "id": "uuid",
      "content": "texte de la question",
      "category": "TECHNICAL|BEHAVIORAL|CULTURAL|MOTIVATION",
      "requiredSkills": ["compétence1", "compétence2"],
      "orderIndex": 1
    }
  ]
}

Les questions doivent être:
- Spécifiques au poste et à l'entreprise
- Variées dans leur difficulté
- Orientées vers l'évaluation concrète des compétences
- Professionnelles et respectueuses
`;

export const buildQuestionPrompt = (
  jobTitle: string,
  company: string,
  jobDescription: string,
  level: string,
  questionCount: number
): string => {
  return QUESTION_GENERATION_PROMPT
    .replace('{jobTitle}', jobTitle)
    .replace('{company}', company)
    .replace('{jobDescription}', jobDescription)
    .replace('{level}', level)
    .replace('{questionCount}', questionCount.toString());
};
