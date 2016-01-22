package edu.stanford.nlp.trees;

import edu.stanford.nlp.ling.Label;

import junit.framework.TestCase;

import java.util.Set;
import java.util.HashSet;

/**
 * Test Tree.java
 *
 * @author Christopher Manning
 */
public class TreeTest extends TestCase {

  /** Test that using an iterator() straight off a tree gives the same
   *  results as building a subTrees collection and then doing an
   *  iterator off of that.
   */
  @SuppressWarnings("null")
  public void testTreeIterator() {
    Tree t = null;
    try {
      t = Tree.valueOf("(ROOT (S (NP (DT The) (ADJP (RB very) (JJ proud)) (NN woman)) (VP (VBD yawned) (ADVP (RB loudly))) (. .)))");
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (t == null) {
      fail("testTreeIterator failed to construct tree");
    }
    Set<Tree> m1 = new HashSet<Tree>();
    Set<Tree> m2 = new HashSet<Tree>();
    // build iterator List
    for (Tree sub : t) {
      m1.add(sub);
    }
    for (Tree sub : t.subTrees()) {
      m2.add(sub);
    }
    assertEquals(m1, m2);
  }

  @SuppressWarnings("null")
  public void testDeeperCopy() {
    Tree t1 = null;
    try {
      t1 = Tree.valueOf("(ROOT (S (NP I) (VP ran)))");
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (t1 == null) {
      fail("testDeeperCopy failed to construct tree");
    }
    Tree t2 = t1.deepCopy();
    assertEquals(t1, t2);               // make sure trees are equal
    assertTrue(t1 != t2);               // make sure trees are not ==
    Label l1 = t1.firstChild().firstChild().firstChild().label();
    Label l2 = t2.firstChild().firstChild().firstChild().label();
    assertEquals(l1, l2);               // make sure labels are equal (redundant)
    assertTrue(l1 != l2);               // make sure labels are not ==
  }

}