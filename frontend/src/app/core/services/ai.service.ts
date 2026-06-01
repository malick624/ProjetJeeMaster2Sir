import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AiService {
  
  private apiUrl = 'http://localhost:8080/api';
  
  constructor(private http: HttpClient) { }
  
  /**
   * Génère des questions d'entretien basées sur la description du poste
   * @param jobDescription Description du poste
   * @param company Entreprise visée
   * @param level Niveau du poste
   * @returns Liste des questions générées
   */
  generateQuestions(jobDescription: string, company: string, level: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/entretiens/generate-questions`, {
      jobDescription,
      company,
      level
    });
  }
  
  /**
   * Évalue une réponse du candidat
   * @param question Question posée
   * @param answer Réponse du candidat
   * @returns Évaluation de la réponse
   */
  evaluateAnswer(question: string, answer: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/entretiens/evaluate-answer`, {
      question,
      answer
    });
  }
  
  /**
   * Génère un rapport d'entretien complet
   * @param interviewId ID de l'entretien
   * @returns Rapport généré
   */
  generateReport(interviewId: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/entretiens/${interviewId}/rapport`);
  }
  
  /**
   * Analyse la description du poste pour extraire les compétences clés
   * @param jobDescription Description du poste
   * @returns Compétences extraites
   */
  analyzeJobDescription(jobDescription: string): Observable<any> {
    return this.http.post(`${this.apiUrl}/ai/analyze-job`, {
      jobDescription
    });
  }
}
