/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elbaldi.interfaces;

import elbaldi.models.bonplan;
import java.util.List;


/**
 *
 * @author user
 */
public interface InterfaceBonplanCrud {
   public void ajouterBonplan(bonplan B);
   public void modifierBonplan(bonplan B);
   public List<bonplan> afficherBonplan();
   public void supprimerbonplan(int id);
}
