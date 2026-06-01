/**
 * Modèles partagés pour les entretiens
 */

export interface Question {
  id: string;
  content: string;
  category: 'TECHNICAL' | 'BEHAVIORAL' | 'CULTURAL' | 'MOTIVATION';
  requiredSkills: string[];
  orderIndex: number;
}

export interface Answer {
  id: string;
  questionId: string;
  content: string;
  timestamp: Date;
}

export interface InterviewSession {
  id: string;
  candidateId: string;
  jobTitle: string;
  company: string;
  description: string;
  questions: Question[];
  answers: Answer[];
  mode: 'CHAT' | 'VOICE' | 'AVATAR';
  status: 'CREATED' | 'IN_PROGRESS' | 'COMPLETED' | 'CANCELLED';
  createdAt: Date;
  startedAt?: Date;
  completedAt?: Date;
  duration?: number;
}

export interface Evaluation {
  relevance: number;
  completeness: number;
  clarity: number;
  concreteExamples: number;
  technicalSkills: number;
  overallScore: number;
}

export interface Feedback {
  strengths: string[];
  improvements: string[];
  constructiveComment: string;
}

export interface AnswerEvaluation {
  evaluation: Evaluation;
  feedback: Feedback;
  demonstratedSkills: string[];
}

export interface InterviewReport {
  executiveSummary: string;
  detailedAnalysis: {
    technicalSkills: {
      score: number;
      comments: string;
    };
    behavioralSkills: {
      score: number;
      comments: string;
    };
    culturalFit: {
      score: number;
      comments: string;
    };
  };
  recommendation: 'HIRE' | 'CONSIDER' | 'REJECT';
  recommendationJustification: string;
  developmentPlan: string[];
  overallScore: number;
}
