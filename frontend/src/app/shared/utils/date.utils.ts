/**
 * Utilitaires pour les dates
 */

export class DateUtils {
  
  /**
   * Formate une date en format lisible
   */
  static formatDate(date: Date): string {
    return new Date(date).toLocaleDateString('fr-FR', {
      year: 'numeric',
      month: 'long',
      day: 'numeric'
    });
  }
  
  /**
   * Formate une date avec l'heure
   */
  static formatDateTime(date: Date): string {
    return new Date(date).toLocaleString('fr-FR', {
      year: 'numeric',
      month: 'long',
      day: 'numeric',
      hour: '2-digit',
      minute: '2-digit'
    });
  }
  
  /**
   * Calcule la durée entre deux dates en minutes
   */
  static calculateDurationInMinutes(start: Date, end: Date): number {
    const diffMs = new Date(end).getTime() - new Date(start).getTime();
    return Math.floor(diffMs / 60000);
  }
  
  /**
   * Formate une durée en minutes en format lisible
   */
  static formatDuration(minutes: number): string {
    const hours = Math.floor(minutes / 60);
    const mins = minutes % 60;
    
    if (hours > 0) {
      return `${hours}h ${mins}min`;
    }
    return `${mins} min`;
  }
}
