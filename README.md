ENDEWEM
=======

Implementation is based on "Methods for Determining the Statistical Significance of Enrichment or Depletion of Gene Ontology Classifications under Weighted Membership." Iacucci E1, Zingg HH, Perkins TJ.


---

Original code accompanying the publication is located at 
https://sites.google.com/site/statisticsformembership/
Note that it is distributed under GPL LICENSE.

USAGE
-----

Statforweights.java
-------------------
This program comes with 2 examples "examplePhi2|Phi2" and "examplePhi2|Phi1" which provide insight into how to compute the p-values presented in the manuscript using the methods in the .java file.

Computing p-values under Phi2|Phi1
----------------------------------

To compute values using this method the following parameters are needed:

Hashmap a // the hashmap of the genes mapped at or below v and the number of paths reachable from each of them
Int n_genes // the number of genes which are "members" of v
Int v_genes // the total number of paths reachable from v 
Int r_genes // the number of genes which are "members" of the the root

These parameters (a, n_genes, v_genes, r_genes) are used in method "setuptableSS" to create table T.

Individual probabilities are then caluculated using the method "probSS", which takes as parameters T, n_genes, r_genes, n_genes, and the observed number.

Computing p-values under Phi2|Phi2
----------------------------------

To compute values using this method the following parameters are needed:

Hashmap a // the hashmap of the genes mapped at or below v and the number of paths reachable from each of them
Hashmap b // the hashmap of the genes mapped at or below the complement of subgraph rooted at v and the number of paths reachable from each of them
Hashmap c // the hashmap of the genes mapped at or below the root and the number of paths reachable from each of them
Int v_i_genes // the total number of paths reachable from v of the interesting genes
Int nv_genes // the total number of paths reachable from  the complement of the subgraph rooted at v 
Int r_i_genes // the total number of paths reachable from r of the interesting genes 

These parameters (v_i_genes, a,  b) are used in method "setuptable_UncompressedL" to create table T1.

These parameters (r_i_genes, a) are used in method "setuptableEHM" to create table T2.

These parameters (r_i_genes, c) are used in method "setuptableEHM" to create table T3.

Individual probabilities are then caluculated using the method "probL", which takes as parameters  T1, r_i_genes,  observed_value,nv_genes, T2, T3[r_i_genes].


